package com.mathews.recommender.config;

import com.mathews.repositories.ActionsRepository;
import com.mathews.repositories.PostgresActionsRepository;
import com.mathews.repositories.PostgresRecommendationsRepository;
import com.mathews.repositories.RecommendationsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    @Bean
    public RecommendationsRepository recommendationsRepository() {
        return new PostgresRecommendationsRepository();
    }

    @Bean
    public ActionsRepository actionsRepository() {
        return new PostgresActionsRepository();
    }
}
