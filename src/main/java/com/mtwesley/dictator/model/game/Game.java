package com.mtwesley.dictator.model.game;

import com.mtwesley.dictator.model.game.account.Transaction;
import com.mtwesley.dictator.model.player.Player;
import com.mtwesley.dictator.model.player.Role;

import java.util.Comparator;
import java.util.List;

public abstract class Game {

    public enum State {
        STARTING,
        IN_PROGRESS,
        ENDING,
        COMPLETED
    }

    protected Comparator<Role> sort;
    protected Turn currentTurn;
    protected List<Role> roles;
    protected List<Turn> turns;
    protected List<Transaction> transactions;

    public abstract Role assignRole(Player player);

    public abstract Turn nextTurn();
}
