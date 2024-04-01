package com.mtwesley.dictator.model.game;

import com.mtwesley.dictator.model.player.Citizen;
import com.mtwesley.dictator.model.player.Player;
import com.mtwesley.dictator.model.player.Role;

public class DictatorGame extends Game {

    @Override
    public Role assignRole(Player player) {
        // Logic to assign a role to a player
        return new Citizen(player); // Or new Dictator(player) based on some condition
    }

    @Override
    public Turn nextTurn() {
        // Advance the game to the next turn
        return new Turn(); // Implementation details of creating a new turn
    }
}