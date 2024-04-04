package com.mtwesley.dictator.model.board;

import com.mtwesley.dictator.model.board.tile.Tile;
import com.mtwesley.dictator.model.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public abstract class Board {
    private Random random = new Random();
    protected Tile[] tiles;
    protected Map<Player, Position> playerPositions;
    protected int[] playerCounts;
    protected int maxPlayersPerTile;

    public Board(int size, int maxPlayersPerTile) {
        this.tiles = new Tile[size];
        this.playerPositions = new HashMap<>();
        this.playerCounts = new int[size];
        this.maxPlayersPerTile = maxPlayersPerTile;
        initializeTiles();
    }

    protected abstract void initializeTiles();
    protected abstract boolean isValidPosition(Position position);
    protected abstract Position move(Position position, Direction direction);
    public abstract int getIndexFromPosition(Position position);
    public abstract Position getPositionFromIndex(int index);

    protected Position getRandomPosition() {
        return getRandomPosition(false);
    }

    protected Position getRandomPosition(boolean isVacant) {
        int start = random.nextInt(tiles.length);
        if (isVacant) {
            for (int i = 0; i < tiles.length; i++) {
                int index = (start + i) % tiles.length;
                if (playerCounts[index] < maxPlayersPerTile) {
                    return getPositionFromIndex(index);
                }
            }
        } else {
            return getPositionFromIndex(start);
        }
        return null;
    }

    public boolean movePlayer(Player player, Direction direction) {
        Position currentPosition = playerPositions.get(player);
        if (currentPosition == null) {
            return false;
        }
        Position newPosition = move(currentPosition, direction);
        if (newPosition == null || !isValidPosition(newPosition)) {
            return false;
        }

        int currentIndex = getIndexFromPosition(currentPosition);
        int newIndex = getIndexFromPosition(newPosition);

        if (playerCounts[newIndex] < maxPlayersPerTile) {
            playerCounts[currentIndex]--;
            playerCounts[newIndex]++;
            playerPositions.put(player, newPosition);
            return true;
        }
        return false;
    }

    public boolean isFull() {
        for (int count : playerCounts) {
            if (count < maxPlayersPerTile) {
                return false;
            }
        }
        return true;
    }

    public boolean join(Player player) {
        Position position = getRandomPosition();
        if (position != null) {
            int index = getIndexFromPosition(position);
            if (playerCounts[index] < maxPlayersPerTile) {
                playerCounts[index]++;
                playerPositions.put(player, position);
                return true;
            }
        }
        return false;
    }

    public boolean leave(Player player) {
        Position position = playerPositions.remove(player);
        if (position != null) {
            int index = getIndexFromPosition(position);
            playerCounts[index]--;
            return true;
        }
        return false;
    }

}