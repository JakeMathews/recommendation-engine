package com.mathews.recommender.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mathews.recommender.serialization.DurationHoursSerializer;

import java.time.Duration;

// Not the best format. Returning the actual lists of recommendations made and accepted would be better.
// Fields like acceptanceRate could then be inferred by clients calling the API rather than it being calculated server side.
// But this format definitely meets to outlined requirements, while inference isn't what's being requested
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
