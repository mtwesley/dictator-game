package com.mtwesley.dictator.service;

import com.mtwesley.dictator.model.board.Board;
import com.mtwesley.dictator.model.board.Position;
import com.mtwesley.dictator.model.board.Tile;
import com.mtwesley.dictator.repository.BoardRepository;
import com.mtwesley.dictator.repository.TileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private TileRepository tileRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${board.default.width}")
    private int defaultWidth;

    @Value("${board.default.height}")
    private int defaultHeight;

    @Value("${board.default.coinProbability}")
    private double defaultCoinProbability;

    @Value("${board.default.totalCoins}")
    private int defaultTotalCoins;

    @Value("${board.default.minCoinsPerTile}")
    private int defaultMinCoinsPerTile;

    @Value("${board.default.maxCoinsPerTile}")
    private int defaultMaxCoinsPerTile;

    @PostConstruct
    @Transactional
    public void initializeDefaultBoard() {
        if (boardRepository.count() == 0) {
            Board board = createBoard(defaultWidth, defaultHeight);
            List<Tile> tiles = createBoardTiles(board, defaultWidth * defaultHeight,
                    defaultCoinProbability, defaultTotalCoins,
                    defaultMinCoinsPerTile, defaultMaxCoinsPerTile);
            if (!validateBoard(board, tiles)) {
                throw new IllegalStateException("Failed to validate the new board.");
            }
            tiles.forEach(tile -> board.getTileIds().add(tile.getId()));
            tileRepository.saveAll(tiles);
            boardRepository.save(board);
        }
    }

    public Board getDefaultBoard() {
        return boardRepository.findAll().stream()
                .findFirst()
                .orElse(null);
    }

    public Board getBoardById(String defaultBoardId) {
        return boardRepository.findById(defaultBoardId).orElse(null);
    }

    public List<Tile> getTilesByBoardId(String boardId) {
        return tileRepository.findByBoardId(boardId);
    }

    public Board createBoard(int width, int height) {
        Board board = new Board();
        board.setWidth(width);
        board.setHeight(height);
        return board;
    }

    public List<Tile> createBoardTiles(Board board, int numTiles, double coinProbability,
                                       int totalCoins, int minCoins, int maxCoins) {
        List<Tile> tiles = new ArrayList<>();
        Random random = new Random();
        int remainingCoins = totalCoins;

        for (int i = 0; i < numTiles; i++) {
            Tile tile = new Tile();
            Position position = new Position(i % board.getWidth(), i / board.getWidth());
            tile.setPosition(position);
            tile.setBoardId(board.getId());

            if (random.nextDouble() < coinProbability && remainingCoins > 0) {
                int coinsToAllocate = random.nextInt((maxCoins - minCoins) / 10 + 1) * 10 + minCoins;
                coinsToAllocate = Math.min(coinsToAllocate, remainingCoins);
                tile.setCoins(coinsToAllocate);
                remainingCoins -= coinsToAllocate;
            }

            tiles.add(tile);
        }

        return tiles;
    }

    public boolean validateBoard(Board board, List<Tile> tiles) {
        if (tiles.isEmpty()) {
            return true;
        } else if (tiles.size() == board.getWidth() * board.getHeight()) {
            return tiles.stream().allMatch(tile -> board.isValidPosition(tile.getPosition()));
        }
        return false;
    }
}
