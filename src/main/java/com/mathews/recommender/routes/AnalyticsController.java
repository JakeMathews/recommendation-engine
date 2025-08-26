package com.mathews.recommender.routes;

import com.mathews.recommender.models.AnalyticsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {
    @GetMapping("/member/{memberId}")
    public ResponseEntity<AnalyticsResponse> getMemberAnalytics(@PathVariable String memberId) {
        // TODO: Make service call to get analytics for a given member
        return ResponseEntity.ok(null);
    }

    @GetMapping("/global")
    public ResponseEntity<AnalyticsResponse> getGlobalAnalytics() {
        // TODO: Stretch goal, also return a simple 7-day moving acceptance rate (so we can plot a trend).
        return ResponseEntity.ok(null);
    }
}
