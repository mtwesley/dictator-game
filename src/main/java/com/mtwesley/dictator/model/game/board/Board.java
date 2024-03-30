package com.mtwesley.dictator.model.game.board;

import com.mtwesley.dictator.model.game.board.tile.Tile;
import com.mtwesley.dictator.model.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Board {
    private final int width;
    private final int height;
    private final int level;
    private Tile[] tiles;
    private Set<Player> players;
    private int maxPlayersPerTile;

    public Board(int width, int height, int level, int maxPlayersPerTile) {
        this.width = width;
        this.height = height;
        this.level = level;
        this.tiles = new Tile[width * height];
        this.players = new HashSet<>();
        this.maxPlayersPerTile = maxPlayersPerTile;
        initializeTiles();
    }

    private void initializeTiles() {
        for (int i = 0; i < tiles.length; i++) {
            int x = i % width;
            int y = i / width;
            tiles[i] = new Tile(this, new Position(x, y));
        }
    }

    public boolean join(Player player) {
        if (players.size() < maxPlayersPerTile) {
            return players.add(player);
        }
        return false;
    }

    public void leave(Player player) {
        players.remove(player);
    }

    private boolean isValidPosition(Position position) {
        int index = position.getY() * width + position.getX();
        return position.getX() >= 0 && position.getX() < width &&
                position.getY() >= 0 && position.getY() < height &&
                index >= 0 && index < tiles.length;
    }

    private Position move(Position position, Direction direction) {
        int newX = position.getX();
        int newY = position.getY();

        if (direction == Direction.LEFT) {
            newX--;
        } else if (direction == Direction.RIGHT) {
            newX++;
        } else if (direction == Direction.UP) {
            newY--;
        } else if (direction == Direction.DOWN) {
            newY++;
        }

        Position newPosition = new Position(newX, newY);
        if (isValidPosition(newPosition)) {
            return newPosition;
        } else {
            throw new IllegalArgumentException("Invalid move");
        }
    }

    public void movePlayer(Player player, Direction direction) {
        // Implement movement logic, updating player's position based on direction
        // and invoking play on the relevant tile
    }

    private int getPositionIndex(Position position) {
        if (!isValidPosition(position)) {
            throw new IllegalArgumentException("Invalid position");
        }
        return position.getY() * width + position.getX();
    }
}
