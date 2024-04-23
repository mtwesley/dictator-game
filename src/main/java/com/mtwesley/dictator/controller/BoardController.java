package com.mtwesley.dictator.controller;

import com.mtwesley.dictator.model.board.Direction;
import com.mtwesley.dictator.model.board.Position;
import com.mtwesley.dictator.model.player.Player;

import java.util.ArrayList;
import java.util.Random;

public class BoardController {

//    protected int[] playersPerTileCounts;
//    protected int maxPlayersPerTile;
//
//    protected Random random = new Random();
//
//    public Board(int size, int maxPlayersPerTile) {
//        this.size = size;
//        this.tiles = new ArrayList<>(size);
//        this.playersPerTileCounts = new int[size];
//        this.maxPlayersPerTile = maxPlayersPerTile;
//    }
//
//    protected abstract void initializeTiles();
//    protected abstract boolean isValidPosition(Position position);
//    protected abstract Position move(Position position, Direction direction);
//    public abstract int getIndexFromPosition(Position position);
//    public abstract Position getPositionFromIndex(int index);
//
//    protected Position getRandomPosition() {
//        return getRandomPosition(false);
//    }
//
//    protected Position getRandomPosition(boolean isVacant) {
//        int start = random.nextInt(size);
//        if (isVacant) {
//            for (int i = 0; i < size; i++) {
//                int index = (start + i) % size;
//                if (playersPerTileCounts[index] < maxPlayersPerTile) {
//                    return getPositionFromIndex(index);
//                }
//            }
//        } else {
//            return getPositionFromIndex(start);
//        }
//        return null;
//    }
//
//    public boolean movePlayer(Player player, Direction direction) {
//        Position currentPosition = playerPositions.get(player.getId());
//        if (currentPosition == null) {
//            return false;
//        }
//        Position newPosition = move(currentPosition, direction);
//        if (newPosition == null || !isValidPosition(newPosition)) {
//            return false;
//        }
//
//        int currentIndex = getIndexFromPosition(currentPosition);
//        int newIndex = getIndexFromPosition(newPosition);
//
//        if (playersPerTileCounts[newIndex] < maxPlayersPerTile) {
//            playersPerTileCounts[currentIndex]--;
//            playersPerTileCounts[newIndex]++;
//            playerPositions.put(player.getId(), newPosition);
//            return true;
//        }
//        return false;
//    }
//
//    public boolean isFull() {
//        for (int count : playersPerTileCounts) {
//            if (count < maxPlayersPerTile) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public boolean join(Player player) {
//        Position position = getRandomPosition();
//        if (position != null) {
//            int index = getIndexFromPosition(position);
//            if (playersPerTileCounts[index] < maxPlayersPerTile) {
//                playersPerTileCounts[index]++;
//                playerPositions.put(player.getId(), position);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public boolean leave(Player player) {
//        Position position = playerPositions.remove(player.getId());
//        if (position != null) {
//            int index = getIndexFromPosition(position);
//            playersPerTileCounts[index]--;
//            return true;
//        }
//        return false;
//    }
}
