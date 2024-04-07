package com.mtwesley.dictator.model.board.tile;

import com.mtwesley.dictator.model.board.Board;
import com.mtwesley.dictator.model.board.Position;
import com.mtwesley.dictator.model.game.Game;
import com.mtwesley.dictator.model.player.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document
@TypeAlias("GameTile")
public class GameTile extends Tile {

    @DBRef
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