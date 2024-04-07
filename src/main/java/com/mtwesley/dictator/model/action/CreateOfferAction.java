package com.mtwesley.dictator.model.action;

import com.mtwesley.dictator.model.game.Game;
import com.mtwesley.dictator.model.transaction.Offer;
import com.mtwesley.dictator.model.player.Dictator;
import com.mtwesley.dictator.model.player.Role;
import org.springframework.data.annotation.TypeAlias;

@TypeAlias("CreateOfferAction")
public class CreateOfferAction extends OfferAction {
    public CreateOfferAction(Role role, Game game, Offer offer) {
        super("CREATE_OFFER", role, game, offer);
    }

    @Override
    public void commit() {
        if (!(getRole() instanceof Dictator)) {
            throw new IllegalStateException("Only Dictators can create offers.");
        }
    }
}