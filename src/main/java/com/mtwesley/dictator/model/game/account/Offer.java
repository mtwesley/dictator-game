package com.mtwesley.dictator.model.game.account;

import com.mtwesley.dictator.model.player.Player;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Offer extends Transaction {

    public Offer(int amount, Player from, Player to) {
        super("OFFER", amount, from, to);
    }

    public Player getFromPlayer() {
        return (Player) from;
    }

    public Player getToPlayer() {
        return (Player) to;
    }

}