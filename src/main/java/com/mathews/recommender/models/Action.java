package com.mathews.recommender.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public record Action(
    String eventId,
    String memberId,
    String itemId,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Instant timestamp
) {
}