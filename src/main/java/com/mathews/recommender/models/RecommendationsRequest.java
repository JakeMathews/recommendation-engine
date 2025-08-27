package com.mathews.recommender.models;

import java.util.List;

public record RecommendationsRequest(
    List<RecommendationRequest> items
) {
}
