package com.mathews.repositories;

import com.mathews.errors.DuplicateActionException;
import com.mathews.models.Action;

public interface ActionRepository {
    void addAction(Action action) throws DuplicateActionException;
}
