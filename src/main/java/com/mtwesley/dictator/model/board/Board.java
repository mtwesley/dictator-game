package com.mtwesley.dictator.model.board;

import com.mtwesley.dictator.model.board.tile.Tile;
import com.mtwesley.dictator.model.player.Player;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document("boards")
@TypeAlias("Board")
public abstract class Board {
    @Id
    private String id;
    @Transient
    private Random random = new Random();
    @DBRef
    protected Set<Player> players;
    protected Map<String, Position> playerPositions;
    protected Tile[] tiles;
    @Transient
    protected int[] playersPerTileCounts;
    protected int maxPlayersPerTile;

    public Board(int size, int maxPlayersPerTile) {
        this.tiles = new Tile[size];
        this.playerPositions = new HashMap<>();
        this.playersPerTileCounts = new int[size];
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
                if (playersPerTileCounts[index] < maxPlayersPerTile) {
                    return getPositionFromIndex(index);
                }
            }
        } else {
            return getPositionFromIndex(start);
        }
        return null;
    }

    public boolean movePlayer(Player player, Direction direction) {
        Position currentPosition = playerPositions.get(player.getId());
        if (currentPosition == null) {
            return false;
        }
        Position newPosition = move(currentPosition, direction);
        if (newPosition == null || !isValidPosition(newPosition)) {
            return false;
        }

        int currentIndex = getIndexFromPosition(currentPosition);
        int newIndex = getIndexFromPosition(newPosition);

        if (playersPerTileCounts[newIndex] < maxPlayersPerTile) {
            playersPerTileCounts[currentIndex]--;
            playersPerTileCounts[newIndex]++;
            playerPositions.put(player.getId(), newPosition);
            return true;
        }
        return false;
    }

    public boolean isFull() {
        for (int count : playersPerTileCounts) {
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
            if (playersPerTileCounts[index] < maxPlayersPerTile) {
                playersPerTileCounts[index]++;
                playerPositions.put(player.getId(), position);
                return true;
            }
        }
        return false;
    }

    public boolean leave(Player player) {
        Position position = playerPositions.remove(player.getId());
        if (position != null) {
            int index = getIndexFromPosition(position);
            playersPerTileCounts[index]--;
            return true;
        }
        return false;
    }

}