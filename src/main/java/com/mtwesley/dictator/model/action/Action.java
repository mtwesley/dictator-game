package com.mtwesley.dictator.model.action;

import com.mtwesley.dictator.model.game.Game;
import com.mtwesley.dictator.model.player.Player;
import lombok.Getter;

@Getter
public abstract class Action {

    protected class ActionPayload {
        public final String playerId;
        public final String gameId;

        public ActionPayload() {
            this.playerId = player.getId();
            this.gameId = game.getId();
        }
    }

    protected final String action;
    protected final Player player;
    protected final Game game;
    protected final boolean grouped;
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