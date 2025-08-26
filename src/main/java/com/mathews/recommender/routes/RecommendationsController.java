package com.mathews.recommender.routes;

import com.mathews.recommender.models.RecommendationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recommendations")
public class RecommendationsController {
    @PostMapping
    public ResponseEntity<Void> addRecommendations(@RequestBody RecommendationRequest recommendationRequest) {
        // TODO: Make service call to persist recommendation
        return ResponseEntity.ok().build();
    }
}
