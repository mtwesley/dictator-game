package com.mtwesley.dictator.model.game.board;

import lombok.Value;

@Value
public class Position {
    int x;
    int y;

    public Position moveTo(Direction direction, Board board) {
        int newX = x;
        int newY = y;

        if (direction == Direction.LEFT) {
            newX = x - 1;
        } else if (direction == Direction.RIGHT) {
            newX = x + 1;
        } else if (direction == Direction.UP) {
            newY = y - 1;
        } else if (direction == Direction.DOWN) {
            newY = y + 1;
        }

        Position newPosition = new Position(newX, newY);
        if (board.isValidPosition(newPosition)) {
            return newPosition;
        } else {
            throw new IllegalArgumentException("Invalid move");
        }
    }
}
