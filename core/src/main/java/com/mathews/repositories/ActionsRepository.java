package com.mathews.repositories;

import com.mathews.errors.DuplicateActionException;
import com.mathews.models.Action;

import java.util.List;

public interface ActionsRepository {
    void addAction(Action action) throws DuplicateActionException;

    // Used only for analytics
    List<Action> getActionsByMemberId(String memberId);
}
