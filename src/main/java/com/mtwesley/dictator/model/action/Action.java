package com.mtwesley.dictator.model.action;

import com.mtwesley.dictator.model.game.Game;
import com.mtwesley.dictator.model.player.Player;
import lombok.Getter;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@TypeAlias("Action")
public abstract class Action {

    @TypeAlias("ActionPayload")
    protected class ActionPayload {
        public final String playerId;
        public final String gameId;

        public ActionPayload() {
            this.playerId = player.getId();
            this.gameId = game.getId();
        }
    }

    protected final String action;
    @DBRef
    protected final Player player;
    @DBRef
    protected final Game game;
    @Transient
    protected final boolean grouped;
    @Transient
    protected final boolean multiple;

    public Action(String action, Player player, Game game) {
        this.action = action;
        this.player = player;
        this.game = game;
        this.grouped = false;
        this.multiple = false;
    }

    protected Action(String action, Player player, Game game, boolean grouped, boolean multiple) {
        this.action = action;
        this.player = player;
        this.game = game;
        this.grouped = grouped;
        this.multiple = multiple;
    }

    public abstract void commit();

    public Object getPayload() {
        return new ActionPayload();
    }

}