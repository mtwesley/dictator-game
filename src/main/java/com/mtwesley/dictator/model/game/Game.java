package com.mtwesley.dictator.model.game;

import com.mtwesley.dictator.model.game.account.Transaction;
import com.mtwesley.dictator.model.player.Player;
import com.mtwesley.dictator.model.player.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Game {

    public enum GameState {
        STARTING,
        IN_PROGRESS,
        ENDING,
        COMPLETED
    }

    protected String id;
    protected Turn currentTurn;
    protected List<Role> roles = new ArrayList<>();
    protected List<Turn> turns = new ArrayList<>();
    protected List<Transaction> transactions = new ArrayList<>();
    protected GameState state = GameState.STARTING;

    public abstract Role assignRole(Player player);
    public abstract Turn nextTurn();
    public abstract Comparator<Role> getRoleComparator();

}
