package com.mtwesley.dictator.model.transaction;

import com.mtwesley.dictator.model.player.Player;
import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;


@TypeAlias("Offer")
public class Offer extends Transaction {

    public Offer(int amount, Player from, Player to) {
        super("OFFER", amount, from, to);
    }

    public Player getFromPlayer() {
        return (Player) fromPlayer;
    }

    public Player getToPlayer() {
        return (Player) toPlayer;
    }

}
