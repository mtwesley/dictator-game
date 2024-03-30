package com.mtwesley.dictator.model.game.board.tile;

import com.mtwesley.dictator.model.game.board.Board;
import com.mtwesley.dictator.model.game.board.Position;
import com.mtwesley.dictator.model.player.Player;
import lombok.Getter;

@Getter
public class Tile implements Playable {
    protected final Board board;
    protected final Position position;

    public Tile(Board board, Position position) {
        this.board = board;
        this.position = position;
    }

    @Override
    public void play(Player player) {
        // Basic tiles do nothing when played
    }
}
