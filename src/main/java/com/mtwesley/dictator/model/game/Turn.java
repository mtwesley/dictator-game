package com.mtwesley.dictator.model.game;

import com.mtwesley.dictator.model.game.action.Action;
import com.mtwesley.dictator.model.player.Role;

import java.time.LocalDateTime;
import java.util.List;

public class Turn {
    private Role role;
    private List<Action> actions;
    private LocalDateTime start;
    private LocalDateTime end;

    public void startTurn() {
        this.start = LocalDateTime.now();
        // Start turn logic
    }

    public void endTurn() {
        this.end = LocalDateTime.now();
        // End turn logic
    }
}