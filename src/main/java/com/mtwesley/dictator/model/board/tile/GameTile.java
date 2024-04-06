package com.mtwesley.dictator.model.board.tile;

import com.mtwesley.dictator.model.board.Board;
import com.mtwesley.dictator.model.board.Position;
import com.mtwesley.dictator.model.game.Game;
import com.mtwesley.dictator.model.player.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameTile extends Tile {
    private Game game;

    public GameTile(Board board, Position position, Game game) {
        super(board, position);
        this.game = game;
    }

    @Override
    public void play(Player player) {
        // Implement game-specific interactions here
        // add player to game
        // game.addPlayer(player)
        // check if game is full
        // start the game if full
        // game.start();
    }
}