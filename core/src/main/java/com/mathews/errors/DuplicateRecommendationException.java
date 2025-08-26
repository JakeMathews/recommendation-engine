package com.mathews.errors;

public class DuplicateRecommendationException extends Exception {
    public DuplicateRecommendationException(String recommendationId) {
        super(String.format("Duplicate recommendation with id %s", recommendationId));
    }
}
