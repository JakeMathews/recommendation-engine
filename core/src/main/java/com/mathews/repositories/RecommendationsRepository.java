package com.mathews.repositories;

import com.mathews.errors.DuplicateRecommendationException;
import com.mathews.models.Recommendation;

import java.util.List;
import java.util.Optional;

public interface RecommendationsRepository {
    // Considered adding a bulk addRecommendations method to the handler, but decided against it due to time constraints.
    // It would likely attempt to save all the recommendations in one transaction, which could be rolled back if there
    //      were any errors (like duplicative entries).
    void saveRecommendation(Recommendation recommendation) throws DuplicateRecommendationException;

    Optional<Recommendation> getRecommendationById(String recommendationId);

    List<Recommendation> getRecommendationsByMemberId(String memberId);
}
