package com.mathews.models;

import java.time.Instant;
import java.util.List;

public record Recommendation(
    String recommendationId,
    String memberId,
    String itemId,
    List<String> alternateItemIds,
    Instant generatedAt
) {
}
