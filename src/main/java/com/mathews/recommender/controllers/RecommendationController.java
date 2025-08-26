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
public class RecommendationController {
    private final RecommendationHandler recommendationHandler;

    public RecommendationController(RecommendationHandler recommendationHandler) {
        this.recommendationHandler = recommendationHandler;
    }

    @PostMapping
    public ResponseEntity<Void> addRecommendations(@RequestBody ItemsRequest itemRecommendations) {
        itemRecommendations.items().forEach(recommendation ->
            // TODO: Validate not-null
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
