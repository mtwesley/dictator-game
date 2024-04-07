package com.mtwesley.dictator.model.board;

import com.mtwesley.dictator.model.board.tile.Tile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TypeAlias("SquareBoard")
public class SquareBoard extends Board {
    private int width;
    private int height;

    public SquareBoard(int width, int height, int maxPlayersPerTile) {
        super(width * height, maxPlayersPerTile);
        this.width = width;
        this.height = height;
    }

    @Override
    protected void initializeTiles() {
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new Tile(this, new Position(i % width, i / width));
        }
    }

    @Override
    public int getIndexFromPosition(Position position) {
        return position.getY() * width + position.getX();
    }

    @Override
    public Position getPositionFromIndex(int index) {
        if (index < 0 || index >= tiles.length) {
            throw new IllegalArgumentException("Index out of board bounds");
        }
        int x = index % width;
        int y = index / width;
        return new Position(x, y);
    }

    @Override
    protected boolean isValidPosition(Position position) {
        return position.getX() >= 0 && position.getX() < width &&
                position.getY() >= 0 && position.getY() < height;
    }

    @Override
    protected Position move(Position position, Direction direction) {
        int newX = position.getX();
        int newY = position.getY();

        switch (direction) {
            case UP: newY--; break;
            case DOWN: newY++; break;
            case LEFT: newX--; break;
            case RIGHT: newX++; break;
        }

        Position newPosition = new Position(newX, newY);
        if (isValidPosition(newPosition)) {
            return newPosition;
        }
        return null;
    }

    {
        type = "SQUARE";
    }
}
