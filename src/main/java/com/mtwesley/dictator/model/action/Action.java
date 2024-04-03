package com.mtwesley.dictator.model.action;

import com.mtwesley.dictator.model.game.Game;
import com.mtwesley.dictator.model.player.Player;
import lombok.Getter;

@Getter
public abstract class Action {
    protected final String action;
    protected final boolean grouped;
    protected final boolean multiple;

    public Action(String action, boolean grouped, boolean multiple) {
        this.action = action;
        this.grouped = grouped;
        this.multiple = multiple;
    }

    public abstract void commit(Player player, Game game);

}