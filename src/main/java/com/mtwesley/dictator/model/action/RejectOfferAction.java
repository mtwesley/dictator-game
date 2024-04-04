package com.mtwesley.dictator.model.action;

import com.mtwesley.dictator.model.game.Game;
import com.mtwesley.dictator.model.account.Offer;
import com.mtwesley.dictator.model.player.Citizen;
import com.mtwesley.dictator.model.player.Role;

public class RejectOfferAction extends OfferAction {
    public RejectOfferAction(Role role, Game game, Offer offer) {
        super("REJECT_OFFER", role, game, offer);
    }

    @Override
    public void commit() {
        if (!(getRole() instanceof Citizen)) {
            throw new IllegalStateException("Only Citizens can reject offers.");
        }
        // Logic to reject an offer
    }
}