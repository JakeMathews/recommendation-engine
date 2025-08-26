package com.mathews.repositories;

import com.mathews.errors.DuplicateActionException;
import com.mathews.models.Action;

public interface ActionsRepository {
    void addAction(Action action) throws DuplicateActionException;
}
