package com.mathews.recommender.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;

public record RecommendationRequest(
    @JsonProperty("recId") // Matching provided examples
    String recommendationId,
    String memberId,
    String originalId,
    @JsonProperty("alternates") // Matching provided examples
    List<String> alternatIds,
    @JsonFormat(shape = JsonFormat.Shape.STRING) // ensures ISO-8601 text like 2025-08-01T10:00:00Z
    Instant generatedAt
) {
}
