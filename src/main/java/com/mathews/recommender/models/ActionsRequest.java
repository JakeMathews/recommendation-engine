package com.mathews.recommender.models;

import java.util.List;

public record ActionsRequest(List<Action> events) {
}
