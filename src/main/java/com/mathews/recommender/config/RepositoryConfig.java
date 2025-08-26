package com.mathews.recommender.config;

import com.mathews.repositories.RecommendationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    @Bean
    public RecommendationRepository recommendationRepository() {
        return null; // TODO: Implement repository
    }
}
