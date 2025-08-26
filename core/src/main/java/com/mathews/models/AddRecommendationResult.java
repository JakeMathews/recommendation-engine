package com.mathews.models;

public sealed interface AddRecommendationResult permits AddRecommendationResult.Success, AddRecommendationResult.Failure {
    String recommendationId();

    record Success(String recommendationId) implements AddRecommendationResult {
    }

    record Failure(String recommendationId, Exception exception) implements AddRecommendationResult {
    }
}
