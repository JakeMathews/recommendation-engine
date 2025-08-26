package com.mathews.recommender.controllers;

import com.mathews.handlers.RecommendationHandler;
import com.mathews.recommender.models.ItemsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recommendations")
public class RecommendationsController {
    private final RecommendationHandler recommendationHandler;

    public RecommendationsController(RecommendationHandler recommendationHandler) {
        this.recommendationHandler = recommendationHandler;
    }

    @PostMapping
    public ResponseEntity<Void> addRecommendations(@RequestBody ItemsRequest itemRecommendations) {
        itemRecommendations.items().forEach(recommendation ->
            recommendationHandler.addRecommendation(
                recommendation.recommendationId(),
                recommendation.memberId(),
                recommendation.originalId(),
                recommendation.alternatIds(),
                recommendation.generatedAt()
            )
        ); // TODO: Handle errors
        return ResponseEntity.ok().build();
    }
}
