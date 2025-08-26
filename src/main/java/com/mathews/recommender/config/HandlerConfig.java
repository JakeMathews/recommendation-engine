package com.mathews.recommender.config;

import com.mathews.handlers.RecommendationHandler;
import com.mathews.repositories.RecommendationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerConfig {
    @Bean
    public RecommendationHandler recommendationHandler(RecommendationRepository recommendationRepository) {
        return new RecommendationHandler(recommendationRepository);
    }
}
