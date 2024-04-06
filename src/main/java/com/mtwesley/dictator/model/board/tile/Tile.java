package com.mtwesley.dictator.model.board.tile;

import com.mtwesley.dictator.model.board.Board;
import com.mtwesley.dictator.model.board.Position;
import com.mtwesley.dictator.model.player.Player;
import lombok.Getter;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;

@Getter
@TypeAlias("Tile")
public class Tile implements Playable {

    @Transient
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
