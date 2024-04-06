package com.mtwesley.dictator.model.account;

import com.mtwesley.dictator.model.player.Player;
import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

@Getter
@TypeAlias("Endowment")
public class Endowment extends Transaction {

    public Endowment(int amount, Player to) {
        super("ENDOWMENT", amount, Account.SYSTEM, to);
    }

    public Player getToPlayer() {
        return (Player) to;
    }

}