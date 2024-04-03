package com.mtwesley.dictator.model.game.account;

import com.mtwesley.dictator.model.player.Player;
import lombok.Value;

@Value
public class Offer extends Transaction {
    private int amount;
    private Player from;
    private Player to;

}