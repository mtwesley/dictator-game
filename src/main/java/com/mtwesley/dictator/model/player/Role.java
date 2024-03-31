package com.mtwesley.dictator.model.player;

public abstract class Role {
    protected Player player;

    public Role(Player player) {
        this.player = player;
    }

    public abstract void play(Game game);
    // Other character-specific methods
}