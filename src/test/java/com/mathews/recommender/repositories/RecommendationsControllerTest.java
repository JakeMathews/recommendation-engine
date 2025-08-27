package com.mathews.recommender.repositories;

import com.mathews.models.Recommendation;
import com.mathews.recommender.models.RecommendationRequest;
import com.mathews.recommender.models.RecommendationsRequest;
import com.mathews.repositories.RecommendationsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class RecommendationsControllerTest {
    private final Random random = new Random();
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private RecommendationsRepository recommendationsRepository;

    private void assertRecommendationEqualsRequest(Recommendation recommendation, RecommendationRequest recommendationRequest) {
        assertEquals(recommendation.recommendationId(), recommendationRequest.recommendationId());
        assertEquals(recommendation.memberId(), recommendationRequest.memberId());
        assertEquals(recommendation.itemId(), recommendationRequest.originalId());
        assertEquals(recommendation.alternateItemIds(), recommendationRequest.alternatIds());
        assertEquals(recommendation.generatedAt(), recommendationRequest.generatedAt());
    }

    @Test
    void shouldAddRecommendations() {
        RecommendationRequest recommendationRequest = new RecommendationRequest(
            "R-" + random.nextInt(10000),
            "M-42",
            "DRUG-ORIG-1",
            List.of("DRUG-ALT-9", "DRUG-ALT-3", "DRUG-ALT-1"),
            Instant.now()
        );

        RecommendationsRequest recommendationsRequest = new RecommendationsRequest(List.of(recommendationRequest));

        webTestClient.post()
            .uri("/recommendations")
            .bodyValue(recommendationsRequest)
            .exchange()
            .expectStatus().isOk();

        Optional<Recommendation> potentialRecommendation = recommendationsRepository.getRecommendationById(recommendationRequest.recommendationId());
        assertTrue(potentialRecommendation.isPresent());
        Recommendation recommendation = potentialRecommendation.get();
        assertRecommendationEqualsRequest(recommendation, recommendationRequest);
    }

    @Test
    void shouldFailToAddDuplicativeRecommendationsInSameRequest() {
        RecommendationRequest recommendationRequest = new RecommendationRequest(
            "R-" + random.nextInt(10000),
            "M-42",
            "DRUG-ORIG-1",
            List.of("DRUG-ALT-9", "DRUG-ALT-3", "DRUG-ALT-1"),
            Instant.now()
        );

        // Two of the same recommendations
        RecommendationsRequest recommendationsRequest = new RecommendationsRequest(List.of(recommendationRequest, recommendationRequest));

        webTestClient.post()
            .uri("/recommendations")
            .bodyValue(recommendationsRequest)
            .exchange()
            .expectStatus().isBadRequest();

        // The first one should have been successfully saved
        Optional<Recommendation> potentialRecommendation = recommendationsRepository.getRecommendationById(recommendationRequest.recommendationId());
        assertTrue(potentialRecommendation.isPresent());
        Recommendation recommendation = potentialRecommendation.get();
        assertRecommendationEqualsRequest(recommendation, recommendationRequest);
    }

    @Test
    void shouldFailToAddDuplicativeRecommendationsInSubsequentRequests() {
        RecommendationRequest recommendationRequest = new RecommendationRequest(
            "R-" + random.nextInt(10000),
            "M-42",
            "DRUG-ORIG-1",
            List.of("DRUG-ALT-9", "DRUG-ALT-3", "DRUG-ALT-1"),
            Instant.now()
        );

        RecommendationsRequest recommendationsRequest = new RecommendationsRequest(List.of(recommendationRequest));

        webTestClient.post()
            .uri("/recommendations")
            .bodyValue(recommendationsRequest)
            .exchange()
            .expectStatus().isOk();

        Optional<Recommendation> potentialRecommendation = recommendationsRepository.getRecommendationById(recommendationRequest.recommendationId());
        assertTrue(potentialRecommendation.isPresent());
        Recommendation recommendation = potentialRecommendation.get();
        assertRecommendationEqualsRequest(recommendation, recommendationRequest);

        webTestClient.post()
            .uri("/recommendations")
            .bodyValue(recommendationsRequest)
            .exchange()
            .expectStatus().isBadRequest();
    }
}
