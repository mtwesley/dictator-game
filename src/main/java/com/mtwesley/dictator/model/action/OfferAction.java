package com.mtwesley.dictator.model.action;

import com.mtwesley.dictator.model.game.Game;
import com.mtwesley.dictator.model.account.Offer;
import com.mtwesley.dictator.model.player.Role;
import lombok.Getter;

@Getter
public abstract class OfferAction extends Action {
    protected final Offer offer;

    public OfferAction(String action, Role role, Game game, Offer offer, boolean grouped, boolean multiple) {
        super(action, role, game, grouped, multiple);
        this.offer = offer;
    }

    public OfferAction(String action, Role role, Game game, Offer offer) {
        super(action, role, game);
        this.offer = offer;
    }

    @Override
    public abstract void commit();

    @Override
    public Object getPayload() {
        return new ActionPayload() {
            String offerId = offer.getId();
        };
    }

    public Role getRole() {
        return (Role) getPlayer();
    }
}