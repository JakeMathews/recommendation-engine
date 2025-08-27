package com.mathews.repositories;

import com.mathews.errors.DuplicateRecommendationException;
import com.mathews.models.Recommendation;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.mathews.recommender.jooq.public_.Tables.RECOMMENDATIONS;

public class PostgresRecommendationsRepository implements RecommendationsRepository {
    private final DSLContext dsl;

    public PostgresRecommendationsRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public void saveRecommendation(Recommendation recommendation) throws DuplicateRecommendationException {
        try {
            dsl.insertInto(RECOMMENDATIONS)
                .set(RECOMMENDATIONS.RECOMMENDATION_ID, recommendation.recommendationId())
                .set(RECOMMENDATIONS.MEMBER_ID, recommendation.memberId())
                .set(RECOMMENDATIONS.ITEM_ID, recommendation.itemId())
                .set(RECOMMENDATIONS.ALTERNATE_ITEM_IDS, recommendation.alternateItemIds().toArray(new String[0]))
                .set(RECOMMENDATIONS.GENERATED_AT, LocalDateTime.ofInstant(recommendation.generatedAt(), ZoneOffset.UTC))
                .execute();
        } catch (DataAccessException dataAccessException) {
            if (dataAccessException.getMessage().contains("duplicate key")) {
                throw new DuplicateRecommendationException(recommendation.recommendationId());
            }
            throw dataAccessException;
        }
    }

    @Override
    public Optional<Recommendation> getRecommendationById(String recommendationId) {
        return dsl.selectFrom(RECOMMENDATIONS)
            .where(RECOMMENDATIONS.RECOMMENDATION_ID.eq(recommendationId))
            .fetchOptional()
            .map(record -> new Recommendation(
                record.getRecommendationId(),
                record.getMemberId(),
                record.getItemId(),
                record.getAlternateItemIds() != null ? Arrays.asList(record.getAlternateItemIds()) : List.of(),
                record.getGeneratedAt().toInstant(ZoneOffset.UTC)
            ));
    }

    @Override
    public List<Recommendation> getRecommendationsByMemberId(String memberId) {
        return dsl.selectFrom(RECOMMENDATIONS)
            .where(RECOMMENDATIONS.MEMBER_ID.eq(memberId))
            .fetch()
            .map(record -> new Recommendation(
                record.getRecommendationId(),
                record.getMemberId(),
                record.getItemId(),
                record.getAlternateItemIds() != null ? Arrays.asList(record.getAlternateItemIds()) : List.of(),
                record.getGeneratedAt().toInstant(ZoneOffset.UTC)
            ));
    }
}
