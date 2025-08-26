package com.mathews.repositories;

import com.mathews.errors.DuplicateActionException;
import com.mathews.models.Action;

public class PostgresActionRepository implements ActionRepository {
    @Override
    public void addAction(Action action) throws DuplicateActionException {
        // TODO: Implement me
    }
}
