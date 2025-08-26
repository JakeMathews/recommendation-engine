package com.mathews.errors;

public class DuplicateRecommendation extends Exception {
    public DuplicateRecommendation(String recommendationId) {
        super(String.format("Duplicate recommendation with id %s", recommendationId));
    }
}
