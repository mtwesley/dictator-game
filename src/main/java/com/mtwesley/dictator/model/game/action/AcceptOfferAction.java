package com.mtwesley.dictator.model.game.action;

import com.mtwesley.dictator.model.game.Game;
import com.mtwesley.dictator.model.player.Citizen;
import com.mtwesley.dictator.model.player.Role;

public class AcceptOfferAction extends OfferAction {
    public AcceptOfferAction(Offer offer) {
        super("ACCEPT_OFFER", offer, false, false);
    }

    @Override
    public void commit(Role role, Game game) {
        if (!(role instanceof Citizen)) {
            throw new IllegalStateException("Only Citizens can accept offers.");
        }
    }
}