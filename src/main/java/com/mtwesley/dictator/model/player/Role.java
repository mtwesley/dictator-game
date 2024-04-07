package com.mtwesley.dictator.model.player;

import com.mtwesley.dictator.model.game.Game;

public abstract class Role extends Player {

    public Role(Player player) {
        super(player.getId(), player.getName());
    }

    public abstract void play(Game game);
}