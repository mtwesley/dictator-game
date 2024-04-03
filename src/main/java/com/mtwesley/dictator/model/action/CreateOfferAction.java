package com.mtwesley.dictator.model.action;

import com.mtwesley.dictator.model.game.Game;
import com.mtwesley.dictator.model.game.account.Offer;
import com.mtwesley.dictator.model.player.Dictator;
import com.mtwesley.dictator.model.player.Role;

public class CreateOfferAction extends OfferAction {
    public CreateOfferAction(Offer offer) {
        super("CREATE_OFFER", offer, false, true);
    }

    @Override
    public void commit(Role role, Game game) {
        if (!(role instanceof Dictator)) {
            throw new IllegalStateException("Only Dictators can create offers.");
        }
    }
}