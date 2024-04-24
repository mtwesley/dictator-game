package com.mtwesley.dictator.controller;

import com.mtwesley.dictator.model.board.Board;
import com.mtwesley.dictator.model.board.Direction;
import com.mtwesley.dictator.model.board.Position;
import com.mtwesley.dictator.model.board.Tile;
import com.mtwesley.dictator.model.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mtwesley.dictator.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/default")
    public ResponseEntity<Board> getDefaultBoard() {
        Board board = boardService.getDefaultBoard();
        if (board == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(board);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<Board> getBoardById(@PathVariable String boardId) {
        Board board = boardService.getBoardById(boardId);
        if (board == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(board);
    }

    @GetMapping("/{boardId}/tiles")
    public ResponseEntity<List<Tile>> getAllTilesFromBoard(@PathVariable String boardId) {
        List<Tile> tiles = boardService.getTilesByBoardId(boardId);
        if (tiles == null || tiles.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tiles);
    }

}

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

