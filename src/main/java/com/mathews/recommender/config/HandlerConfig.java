package com.mathews.recommender.config;

import com.mathews.handlers.ActionHandler;
import com.mathews.handlers.RecommendationHandler;
import com.mathews.repositories.ActionsRepository;
import com.mathews.repositories.RecommendationsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerConfig {
    @Bean
    public RecommendationHandler recommendationHandler(RecommendationsRepository recommendationsRepository) {
        return new RecommendationHandler(recommendationsRepository);
    }

    @Bean
    public ActionHandler actionHandler(ActionsRepository actionsRepository) {
        return new ActionHandler(actionsRepository);
    }
}
