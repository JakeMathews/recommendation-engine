package com.mathews.recommender.config;

import com.mathews.handlers.ActionHandler;
import com.mathews.handlers.RecommendationHandler;
import com.mathews.repositories.PostgresActionRepository;
import com.mathews.repositories.RecommendationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerConfig {
    @Bean
    public RecommendationHandler recommendationHandler(RecommendationRepository recommendationRepository) {
        return new RecommendationHandler(recommendationRepository);
    }

    @Bean
    public ActionHandler actionHandler(PostgresActionRepository actionRepository) {
        return new ActionHandler(actionRepository);
    }
}
