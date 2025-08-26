package com.mathews.handlers;

import com.mathews.errors.DuplicateRecommendationException;
import com.mathews.models.AddRecommendationResult;
import com.mathews.models.Recommendation;
import com.mathews.repositories.RecommendationRepository;

import java.time.Instant;
import java.util.List;

public class RecommendationHandler {
    private final RecommendationRepository recommendationRepository;

    public RecommendationHandler(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    public AddRecommendationResult addRecommendation(
        String recommendationId,
        String memberId,
        String itemId,
        List<String> alternateItemIds,
        Instant generatedAt
    ) {
        try {
            recommendationRepository.saveRecommendation(
                new Recommendation(
                    recommendationId,
                    memberId,
                    itemId,
                    alternateItemIds,
                    generatedAt
                )
            );
            return new AddRecommendationResult(recommendationId, true);
        } catch (DuplicateRecommendationException duplicateRecommendationException) {
            // TODO: Make result a sealed class
            return new AddRecommendationResult(recommendationId, false);
        }
    }
}
