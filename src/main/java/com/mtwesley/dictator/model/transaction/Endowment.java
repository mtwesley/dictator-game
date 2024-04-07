package com.mtwesley.dictator.model.transaction;

import com.mtwesley.dictator.model.player.Player;
import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;


@TypeAlias("Endowment")
public class Endowment extends Transaction {

    public Endowment(int amount, Player to) {
        super("ENDOWMENT", amount, null, to);
    }

    public Player getToPlayer() {
        return (Player) toPlayer;
    }

}