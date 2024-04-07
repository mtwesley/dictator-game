package com.mtwesley.dictator.model.game;

import com.mtwesley.dictator.model.action.Action;
import com.mtwesley.dictator.model.player.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Document("turns")
public abstract class Turn {

    @Id
    String id;
    @DBRef
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