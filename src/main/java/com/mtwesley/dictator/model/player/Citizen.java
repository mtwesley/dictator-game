package com.mtwesley.dictator.model.player;

public class Citizen extends Character {
    public Citizen(Player player) {
        super(player);
    }

    @Override
    public void play(Game game) {
        // Citizen-specific gameplay implementation
    }
}