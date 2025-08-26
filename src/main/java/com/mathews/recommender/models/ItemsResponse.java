package com.mathews.recommender.models;

import com.mathews.models.Item;

import java.util.List;

public record ItemsResponse(
    List<Item> items
) {
}
