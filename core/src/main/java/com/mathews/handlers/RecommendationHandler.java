package com.mathews.handlers;

import com.mathews.errors.DuplicateRecommendationException;
import com.mathews.models.AddRecommendationResult;
import com.mathews.models.Recommendation;
import com.mathews.repositories.RecommendationsRepository;

import java.time.Instant;
import java.util.List;

public class RecommendationHandler {
    private final RecommendationsRepository recommendationsRepository;

    public RecommendationHandler(RecommendationsRepository recommendationsRepository) {
        this.recommendationsRepository = recommendationsRepository;
    }

    public AddRecommendationResult addRecommendation(
        String recommendationId,
        String memberId,
        String itemId,
        List<String> alternateItemIds,
        Instant generatedAt
    ) {
        try {
            recommendationsRepository.saveRecommendation(
                new Recommendation(
                    recommendationId,
                    memberId,
                    itemId,
                    alternateItemIds,
                    generatedAt
                )
            );
            return new AddRecommendationResult.Success(recommendationId);
        } catch (DuplicateRecommendationException duplicateRecommendationException) {
            return new AddRecommendationResult.Failure(recommendationId, duplicateRecommendationException);
        }
    }
}
