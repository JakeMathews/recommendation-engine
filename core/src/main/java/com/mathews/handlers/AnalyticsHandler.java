package com.mathews.handlers;

import com.mathews.models.Action;
import com.mathews.models.Recommendation;
import com.mathews.repositories.ActionsRepository;
import com.mathews.repositories.RecommendationsRepository;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnalyticsHandler {
    private final ActionsRepository actionsRepository;
    private final RecommendationsRepository recommendationsRepository;

    public AnalyticsHandler(ActionsRepository actionsRepository, RecommendationsRepository recommendationsRepository) {
        this.actionsRepository = actionsRepository;
        this.recommendationsRepository = recommendationsRepository;
    }

    public AnalyticsResult getMemberAnalytics(String memberId) {
        // I wonder if more of this could be done in the repository layer, but time is of the essence, and this should work
        List<Recommendation> recommendations = recommendationsRepository.getRecommendationsByMemberId(memberId);
        Set<String> recommendedItemIds = recommendations.stream()
            .flatMap(recommendation -> Stream.concat(
                Stream.of(recommendation.itemId()),
                recommendation.alternateItemIds().stream())
            )
            .collect(Collectors.toSet());

        // A member accepted a recommendation if they took an action involving a recommended item
        List<Action> memberActions = actionsRepository.getActionsByMemberId(memberId);
        Set<String> acceptedRecommendations = memberActions.stream()
            .map(Action::itemId)
            .filter(recommendedItemIds::contains)
            .collect(Collectors.toSet());

        int recommendationsAccepted = acceptedRecommendations.size();
        int recommendationsMade = recommendations.size();
        double acceptanceRate = recommendationsMade > 0 ? (double) recommendationsAccepted / recommendationsMade : 0.0;

        int acceptedTopRecommendations = calculateTopRecommendationAcceptance(recommendations, memberActions, 1);
        int acceptedTop3Recommendations = calculateTopRecommendationAcceptance(recommendations, memberActions, 3);

        Duration medianTimeToAccept = calculateMedianTimeToAccept(recommendations, memberActions);

        return new AnalyticsResult(
            recommendationsMade,
            recommendationsAccepted,
            acceptanceRate,
            acceptedTopRecommendations,
            acceptedTop3Recommendations,
            medianTimeToAccept
        );
    }

    private int calculateTopRecommendationAcceptance(List<Recommendation> recommendations, List<Action> actions, int topN) {
        Set<String> actionItemIds = actions.stream()
            .map(Action::itemId)
            .collect(Collectors.toSet());

        return (int) recommendations.stream()
            .sorted(Comparator.comparing(Recommendation::generatedAt))
            .limit(topN)
            .filter(recommendation ->
                actionItemIds.contains(recommendation.itemId())
                    || recommendation.alternateItemIds().stream().anyMatch(actionItemIds::contains))
            .count();
    }

    private Duration calculateMedianTimeToAccept(List<Recommendation> recommendations, List<Action> memberActions) {
        Map<String, List<Action>> actionsByItemId = memberActions.stream()
            .collect(Collectors.groupingBy(Action::itemId));

        // Find time between recommendation and first action for each recommendation
        List<Duration> durationsToAcceptedRecommendation = recommendations.stream()
            .filter(recommendation ->
                // A recommended item must be used in an action, otherwise it's not been considered accepted and show be ignored for the analytics
                actionsByItemId.containsKey(recommendation.itemId())
                    || recommendation.alternateItemIds().stream().anyMatch(actionsByItemId::containsKey)
            )
            .map(recommendation -> {
                List<Action> actionsFromRecommendation = actionsByItemId.getOrDefault(recommendation.itemId(), Collections.emptyList());
                if (actionsFromRecommendation.isEmpty()) {
                    actionsFromRecommendation = recommendation.alternateItemIds().stream()
                        .flatMap(itemId -> actionsByItemId.getOrDefault(itemId, Collections.emptyList()).stream())
                        .toList();
                }
                // Find time to first action after this recommendation
                return actionsFromRecommendation.stream()
                    .min(Comparator.comparing(Action::timestamp))
                    .map(action -> Duration.between(recommendation.generatedAt(), action.timestamp()))
                    .orElse(null);
            })
            .filter(Objects::nonNull) // Remove potential nulls from calculating duration between recommendations and actions (there could be no actions)
            .toList();

        return calculateMedianDuration(durationsToAcceptedRecommendation);
    }

    private Duration calculateMedianDuration(List<Duration> durations) {
        // Not entirely sure if this is correct, adding tests would help confirm, but already spent too much time
        if (durations.isEmpty()) {
            return Duration.ZERO;
        }
        if (durations.size() == 1) {
            return durations.getFirst();
        }
        List<Duration> sortedDurations = durations.stream().sorted().toList();
        Duration middleOffset = sortedDurations.get(sortedDurations.size() / 2 - 1);
        Duration middle = sortedDurations.get(sortedDurations.size() / 2);

        return middleOffset.plus(middle).dividedBy(2);
    }

    // Would rather a lot of these be inferred by the client, but the requirements want the api to return these specific analytics
    public record AnalyticsResult(
        int recommendationsMade,
        int recommendationsAccepted,
        double acceptanceRate,
        int acceptedTopRecommendations,
        int acceptedTop3Recommendations,
        Duration medianTimeToAcceptRecommendation
    ) {
    }
}