package com.mathews.repositories;

import com.mathews.errors.DuplicateActionException;
import com.mathews.models.Action;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static com.mathews.recommender.jooq.public_.Tables.ACTIONS;

public class PostgresActionsRepository implements ActionsRepository {
    private final DSLContext dsl;

    public PostgresActionsRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public void addAction(Action action) throws DuplicateActionException {
        try {
            dsl.insertInto(ACTIONS)
                .set(ACTIONS.ACTION_ID, action.actionId())
                .set(ACTIONS.MEMBER_ID, action.memberId())
                .set(ACTIONS.ITEM_ID, action.itemId())
                .set(ACTIONS.TIMESTAMP, LocalDateTime.ofInstant(action.timestamp(), ZoneOffset.UTC))
                .execute();
        } catch (DataAccessException dataAccessException) {
            if (dataAccessException.getMessage().contains("duplicate key")) {
                throw new DuplicateActionException(action.actionId());
            }
            throw dataAccessException;
        }
    }

    @Override
    public List<Action> getActionsByMemberId(String memberId) {
        return dsl.selectFrom(ACTIONS)
            .where(ACTIONS.MEMBER_ID.eq(memberId))
            .fetch(record -> new Action(
                record.getValue(ACTIONS.ACTION_ID),
                record.getValue(ACTIONS.MEMBER_ID),
                record.getValue(ACTIONS.ITEM_ID),
                record.getValue(ACTIONS.TIMESTAMP).toInstant(ZoneOffset.UTC)
            ));
    }
}
