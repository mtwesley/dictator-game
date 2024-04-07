package com.mtwesley.dictator.model.transaction;

import com.mtwesley.dictator.model.player.Player;
import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;


@TypeAlias("Tax")
public class Tax extends Transaction {

    public Tax(int amount, Player to) {
        super("ENDOWMENT", amount, to, null);
    }

    public Player getFromPlayer() {
        return (Player) fromPlayer;
    }

}