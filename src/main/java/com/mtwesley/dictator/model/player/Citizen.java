package com.mtwesley.dictator.model.player;

import com.mtwesley.dictator.model.game.Game;
import org.springframework.data.annotation.TypeAlias;

@TypeAlias("Player")
public class Citizen extends Role {
    public Citizen(Player player) {
        super(player);
    }

    @Override
    public void play(Game game) {
        // Citizen-specific gameplay implementation
    }
}