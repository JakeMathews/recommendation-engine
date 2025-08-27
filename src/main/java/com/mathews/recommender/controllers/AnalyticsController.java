package com.mathews.recommender.controllers;

import com.mathews.handlers.AnalyticsHandler;
import com.mathews.recommender.models.AnalyticsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {
    private final AnalyticsHandler analyticsHandler;

    public AnalyticsController(AnalyticsHandler analyticsHandler) {
        this.analyticsHandler = analyticsHandler;
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<AnalyticsResponse> getMemberAnalytics(@PathVariable String memberId) {
        AnalyticsHandler.AnalyticsResult analyticsResult = analyticsHandler.getMemberAnalytics(memberId);
        return ResponseEntity.ok(
            new AnalyticsResponse(
                analyticsResult.recommendationsMade(),
                analyticsResult.recommendationsAccepted(),
                analyticsResult.acceptanceRate(),
                analyticsResult.acceptedTopRecommendations(),
                analyticsResult.acceptedTop3Recommendations(),
                analyticsResult.medianTimeToAcceptRecommendation()
            )
        );
    }

    @GetMapping("/global")
    public ResponseEntity<AnalyticsResponse> getGlobalAnalytics() {
        // TODO: Stretch goal, also return a simple 7-day moving acceptance rate (so we can plot a trend).
        return ResponseEntity.ok(null);
    }
}
