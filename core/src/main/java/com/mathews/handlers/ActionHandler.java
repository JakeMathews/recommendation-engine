package com.mathews.handlers;

import com.mathews.errors.DuplicateActionException;
import com.mathews.models.Action;
import com.mathews.models.AddActionResult;
import com.mathews.repositories.ActionsRepository;

import java.util.List;

public class ActionHandler {
    private final ActionsRepository actionsRepository;

    public ActionHandler(ActionsRepository actionsRepository) {
        this.actionsRepository = actionsRepository;
    }

    public List<AddActionResult> addActions(List<Action> actions) {
        return actions.stream().map(action -> {
            try {
                actionsRepository.addAction(action);
                return (AddActionResult) new AddActionResult.Success(action.actionId());
            } catch (DuplicateActionException duplicateActionException) {
                return new AddActionResult.Failure(action.actionId(), duplicateActionException);
            }
        }).toList();
    }
}
