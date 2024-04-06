package com.mtwesley.dictator.model.account;

import com.mtwesley.dictator.model.player.Player;
import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

@Getter
@TypeAlias("Tax")
public class Tax extends Transaction {

    public Tax(int amount, Player to) {
        super("ENDOWMENT", amount, to, Account.SYSTEM);
    }

    public Player getFromPlayer() {
        return (Player) from;
    }

}