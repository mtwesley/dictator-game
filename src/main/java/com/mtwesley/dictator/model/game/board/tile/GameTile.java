package com.mtwesley.dictator.model.game.board.tile;

import com.mtwesley.dictator.model.game.board.Board;
import com.mtwesley.dictator.model.game.board.Position;
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
        // game.start(player);
    }
}