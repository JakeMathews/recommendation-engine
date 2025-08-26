package com.mathews.handlers;

import com.mathews.repositories.RecommendationRepository;

import java.time.Instant;
import java.util.List;

public class RecommendationHandler {
    private final RecommendationRepository recommendationRepository;

    public RecommendationHandler(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }
}
