package com.mathews.errors;

public class DuplicateActionException extends Exception {
    public DuplicateActionException(String recommendationId) {
        super(String.format("Duplicate action with id %s", recommendationId));
    }
}
