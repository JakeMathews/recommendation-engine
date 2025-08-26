package com.mathews.recommender.controllers;

import com.mathews.handlers.ActionHandler;
import com.mathews.models.Action;
import com.mathews.recommender.models.ActionsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actions")
public class ActionsController {
    private final ActionHandler actionHandler;

    public ActionsController(ActionHandler actionHandler) {
        this.actionHandler = actionHandler;
    }

    @PostMapping
    public ResponseEntity<Void> addActions(@RequestBody ActionsRequest actionsRequest) {
        actionHandler.addActions(actionsRequest.events().stream()
            .map(request ->
                new Action(
                    request.eventId(),
                    request.memberId(),
                    request.itemId(),
                    request.timestamp()
                )
            ).toList()
        ); // TODO: Handle results
        return ResponseEntity.ok().build();
    }
}
