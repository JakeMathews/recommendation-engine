package com.mathews.recommender.models;

import java.util.List;

public record ItemsRequest(
    List<RecommendationRequest> items
) {
}
