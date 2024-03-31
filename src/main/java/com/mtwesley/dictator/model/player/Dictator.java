package com.mtwesley.dictator.model.player;

public class Dictator extends Role {
    public Dictator(Player player) {
        super(player);
    }

    @Override
    public void play(Game game) {
        // Dictator-specific gameplay implementation
    }

    public Offer makeOffer(int amount, Player offerPlayer) {
        return new Offer(amount, offerPlayer);
    }
}
