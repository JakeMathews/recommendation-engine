package com.mathews.recommender.controllers;

import com.mathews.recommender.models.ActionsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actions")
public class ActionsController {
    @PostMapping
    public ResponseEntity<Void> addActions(@RequestBody ActionsRequest actionsRequest) {
        // TODO: Make service call to persist actions
        return ResponseEntity.ok().build();
    }
}
