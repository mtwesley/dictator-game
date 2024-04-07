package com.mtwesley.dictator.model.player;

import com.mtwesley.dictator.model.game.Game;
import com.mtwesley.dictator.model.transaction.Offer;
import org.springframework.data.annotation.TypeAlias;

@TypeAlias("Player")
public class Dictator extends Role {
    public Dictator(Player player) {
        super(player);
    }

    @Override
    public void play(Game game) {
        // Dictator-specific gameplay implementation
    }

    public Offer makeOffer(int amount, Player toPlayer) {
        return new Offer(amount, this, toPlayer);
    }
}
