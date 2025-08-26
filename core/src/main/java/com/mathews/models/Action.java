package com.mathews.models;

import java.time.Instant;

public record Action(
    String eventId,
    String memberId,
    String itemId,
    Instant timestamp
) {
}