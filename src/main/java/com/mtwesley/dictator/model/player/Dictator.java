package com.mtwesley.dictator.model.player;

import com.mtwesley.dictator.model.game.Game;
import com.mtwesley.dictator.model.transaction.Offer;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

@NoArgsConstructor
@TypeAlias("Dictator")
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
