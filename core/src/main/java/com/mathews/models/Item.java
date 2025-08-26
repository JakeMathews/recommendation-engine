package com.mathews.models;

import java.time.Instant;
import java.util.List;

public record Item(
    String recommendationId,
    String memberId,
    String originalId,
    List<String> alternateIds,
    Instant createdAt
) {
}
