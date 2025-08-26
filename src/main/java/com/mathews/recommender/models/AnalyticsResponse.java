package com.mathews.recommender.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mathews.recommender.serialization.DurationHoursSerializer;

import java.time.Duration;

public record AnalyticsResponse(
    int recommendationsMade,
    int recommendationsAccepted,
    double acceptanceRate,
    int acceptedTopRecommendations,
    int acceptedTop3Recommendations,
    @JsonSerialize(using = DurationHoursSerializer.class)
    Duration medianTimeToAcceptRecommendation
) {

}
