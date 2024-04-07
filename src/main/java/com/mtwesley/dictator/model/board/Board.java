package com.mtwesley.dictator.model.board;

import com.mtwesley.dictator.model.board.tile.Tile;
import com.mtwesley.dictator.model.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("boards")
@TypeAlias("Board")
public abstract class Board {

    // TODO: Still a problem with getting Tiles to show up with the correct _class type (GameTile, for example)
    // When stored into MongoDB it is just stored as an object

    public static class PositionMap extends HashMap<String, Position> {}
    @Id
    protected String id;
    protected String type;
    protected int size;
    @Transient
    protected Random random = new Random();
    @DBRef
    protected Set<Player> players = new HashSet<>();
    protected PositionMap playerPositions = new PositionMap();
    protected List<Tile> tiles;
    @Transient
    protected int[] playersPerTileCounts;
    protected int maxPlayersPerTile;

    public Board(int size, int maxPlayersPerTile) {
        this.size = size;
        this.tiles = new ArrayList<>(size);
        this.playersPerTileCounts = new int[size];
        this.maxPlayersPerTile = maxPlayersPerTile;
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
        int start = random.nextInt(size);
        if (isVacant) {
            for (int i = 0; i < size; i++) {
                int index = (start + i) % size;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Board)) return false;
        Board other = (Board) o;
        return id != null && !id.isEmpty() && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return (id != null && !id.isEmpty()) ? id.hashCode() : System.identityHashCode(this);
    }

    @Override
    public String toString() {
        return id;
    }
}