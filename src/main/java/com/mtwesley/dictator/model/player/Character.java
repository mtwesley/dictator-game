package com.mtwesley.dictator.model.player;

public abstract class Character {
    protected Player player;

    public Character(Player player) {
        this.player = player;
    }

    public abstract void play(Game game);
    // Other character-specific methods
}