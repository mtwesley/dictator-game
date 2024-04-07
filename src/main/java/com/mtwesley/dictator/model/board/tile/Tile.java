package com.mtwesley.dictator.model.board.tile;

import com.mtwesley.dictator.model.board.Board;
import com.mtwesley.dictator.model.board.Position;
import com.mtwesley.dictator.model.player.Playable;
import com.mtwesley.dictator.model.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document("tiles")
@TypeAlias("Tile")
public class Tile implements Playable {

    @Transient
    protected Board board;
    protected Position position;

    public Tile(Board board, Position position) {
        this.board = board;
        this.position = position;
    }

    @Override
    public void play(Player player) {
        // Basic tiles do nothing when played
    }
}
