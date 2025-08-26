package com.mathews.handlers;

import com.mathews.errors.DuplicateActionException;
import com.mathews.models.Action;
import com.mathews.models.AddActionResult;
import com.mathews.repositories.ActionRepository;

import java.util.List;

public class ActionHandler {
    private final ActionRepository actionRepository;

    public ActionHandler(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    public List<AddActionResult> addActions(List<Action> actions) {
        return actions.stream().map(action -> {
            try {
                actionRepository.addAction(action);
                return new AddActionResult(action.actionId(), true);
            } catch (DuplicateActionException duplicateActionException) {
                return new AddActionResult(action.actionId(), false);
            }
        }).toList();
    }
}
