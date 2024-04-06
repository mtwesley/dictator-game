package com.mtwesley.dictator.model.game;

import com.mtwesley.dictator.model.player.Citizen;
import com.mtwesley.dictator.model.player.Dictator;
import com.mtwesley.dictator.model.player.Player;
import com.mtwesley.dictator.model.player.Role;
import org.springframework.data.annotation.TypeAlias;

import java.util.Comparator;

@TypeAlias("DictatorGame")
public class DictatorGame extends Game {

    @Override
    public Role assignRole(Player player) {
        return new Citizen(player);
    }

    @Override
    public int calculateScore(Player player) {
        return 0;
    }

    @Override
    public Turn nextTurn() {
        return new Turn() {};
    }

    @Override
    public Comparator<Role> getRoleComparator() {
        return (a, b) -> a.getClass() == Dictator.class ? 1 : -1;
    }

    {
        type = "DICTATOR";
    }

}