package com.mathews.repositories;

import com.mathews.errors.DuplicateRecommendationException;
import com.mathews.models.Recommendation;

import java.util.List;
import java.util.Optional;

public class PostgresRecommendationRepository implements RecommendationRepository {
    @Override
    public void saveRecommendation(Recommendation recommendation) throws DuplicateRecommendationException {
        // TODO: Implement me
    }

    @Override
    public Optional<Recommendation> getRecommendationById(String recommendationId) {
        // TODO: Implement me
        return Optional.empty();
    }

    @Override
    public List<Recommendation> getRecommendationsByMemberId(String memberId) {
        // TODO: Implement me
        return List.of();
    }
}
