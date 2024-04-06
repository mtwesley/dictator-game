package com.mtwesley.dictator.model.account;

import com.mtwesley.dictator.model.player.Player;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.data.annotation.TypeAlias;

@Value
@AllArgsConstructor
@TypeAlias("Offer")
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
