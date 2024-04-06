package com.mtwesley.dictator.model.player;

import com.mtwesley.dictator.model.game.Game;

public abstract class Role extends Player {
    protected Player player;

    public Role(Player player) {
        this.player = player;
    }

    public abstract void play(Game game);
}