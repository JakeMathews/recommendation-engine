package com.mathews.models;


public sealed interface AddActionResult permits AddActionResult.Success, AddActionResult.Failure {
    String actionId();

    record Success(String actionId) implements AddActionResult {
    }

    record Failure(String actionId, Exception exception) implements AddActionResult {
    }
}
