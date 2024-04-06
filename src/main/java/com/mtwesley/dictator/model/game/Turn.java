package com.mtwesley.dictator.model.game;

import com.mtwesley.dictator.model.action.Action;
import com.mtwesley.dictator.model.player.Role;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Turn {
    protected Role role;
    protected List<Action> actions;
    protected LocalDateTime start;
    protected LocalDateTime end;

    public void startTurn() {
        this.start = LocalDateTime.now();
    }

    public void endTurn() {
        this.end = LocalDateTime.now();
    }
}