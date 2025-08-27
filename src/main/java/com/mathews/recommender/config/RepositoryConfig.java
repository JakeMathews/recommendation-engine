package com.mathews.recommender.config;

import com.mathews.repositories.ActionsRepository;
import com.mathews.repositories.PostgresActionsRepository;
import com.mathews.repositories.PostgresRecommendationsRepository;
import com.mathews.repositories.RecommendationsRepository;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;


@Configuration
public class RepositoryConfig {
    @Bean
    public DSLContext dslContext(DataSource dataSource) {
        return DSL.using(dataSource, SQLDialect.POSTGRES);
    }

    @Bean
    public RecommendationsRepository recommendationsRepository(DSLContext dslContext) {
        return new PostgresRecommendationsRepository(dslContext);
    }

    @Bean
    public ActionsRepository actionsRepository() {
        return new PostgresActionsRepository();
    }
}
