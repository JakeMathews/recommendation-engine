package com.mathews.recommender.controllers;

import com.mathews.handlers.RecommendationHandler;
import com.mathews.models.AddRecommendationResult;
import com.mathews.recommender.models.RecommendationsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {
    private final RecommendationHandler recommendationHandler;

    public RecommendationController(RecommendationHandler recommendationHandler) {
        this.recommendationHandler = recommendationHandler;
    }

    @PostMapping
    public ResponseEntity<Void> addRecommendations(@RequestBody RecommendationsRequest itemRecommendations) {
        List<AddRecommendationResult> addRecommendationResults = itemRecommendations.items().stream().map(recommendation ->
            // TODO: Validate not-null
            recommendationHandler.addRecommendation(
                recommendation.recommendationId(),
                recommendation.memberId(),
                recommendation.originalId(),
                recommendation.alternatIds(),
                recommendation.generatedAt()
            )
        ).toList();

        if (addRecommendationResults.stream().anyMatch(result -> result instanceof AddRecommendationResult.Failure)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
