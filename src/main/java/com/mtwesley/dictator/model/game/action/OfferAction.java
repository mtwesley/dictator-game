package com.mtwesley.dictator.model.game.action;

import com.mtwesley.dictator.model.game.Game;
import com.mtwesley.dictator.model.game.account.Offer;
import com.mtwesley.dictator.model.player.Player;
import com.mtwesley.dictator.model.player.Role;

public abstract class OfferAction extends Action {
    protected Offer offer;

    public OfferAction(String action, Offer offer, boolean grouped, boolean multiple) {
        super(action, grouped, multiple);
        this.offer = offer;
    }

    @Override
    public void commit(Player player, Game game) {
        if (!(player instanceof Role)) {
            throw new IllegalArgumentException("Only roles can interact with offers");
        }
        Role role = (Role) player;
        commit(role, game);
    }

    public abstract void commit(Role role, Game game);
}