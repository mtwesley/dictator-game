package com.mtwesley.dictator.model.action;

import com.mtwesley.dictator.model.game.Game;
import com.mtwesley.dictator.model.game.account.Offer;
import com.mtwesley.dictator.model.player.Citizen;
import com.mtwesley.dictator.model.player.Role;

public class RejectOfferAction extends OfferAction {
    public RejectOfferAction(Offer offer) {
        super("REJECT_OFFER", offer, false, false);
    }

    @Override
    public void commit(Role role, Game game) {
        if (!(role instanceof Citizen)) {
            throw new IllegalStateException("Only Citizens can reject offers.");
        }
        // Logic to reject an offer
    }
}