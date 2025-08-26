package com.mathews.recommender.config;

import com.mathews.handlers.RecommendationHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerConfig {
    @Bean
    public RecommendationHandler recommendationHandler() {
        return new RecommendationHandler();
    }
}
