package com.mathews.models;

import java.time.Instant;

public record Action(
    String actionId,
    String memberId,
    String itemId,
    Instant timestamp
) {
}