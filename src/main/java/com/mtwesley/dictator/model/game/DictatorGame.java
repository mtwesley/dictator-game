package com.mtwesley.dictator.model.game;

import com.mtwesley.dictator.model.player.Citizen;
import com.mtwesley.dictator.model.player.Dictator;
import com.mtwesley.dictator.model.player.Player;
import com.mtwesley.dictator.model.player.Role;

import java.util.Comparator;

public class DictatorGame extends Game {

    @Override
    public Role assignRole(Player player) {
        return new Citizen(player);
    }

    @Override
    public Turn nextTurn() {
        return new Turn() {};
    }

    @Override
    public Comparator<Role> getRoleComparator() {
        return (a, b) -> a.getClass() == Dictator.class ? 1 : -1;
    }
}