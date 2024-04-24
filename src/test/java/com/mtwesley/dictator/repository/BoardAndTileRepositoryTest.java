package com.mtwesley.dictator.repository;

import com.mtwesley.dictator.model.board.Board;
import com.mtwesley.dictator.model.board.Position;
import com.mtwesley.dictator.model.board.Tile;
import com.mtwesley.dictator.service.MetadataService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BoardAndTileRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private TileRepository tileRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MetadataService metadataService;

    @BeforeEach
    public void setUp() {
        mongoTemplate.dropCollection(metadataService.getCollectionName(Board.class));
        mongoTemplate.dropCollection(metadataService.getCollectionName(Tile.class));

        createAndSaveBoardWithTiles("board1", 8, 8); // Assuming a square board
        createAndSaveBoardWithTiles("board2", 10, 5); // Assuming a rectangular board
    }

    private void createAndSaveBoardWithTiles(String boardId, int width, int height) {
        List<String> tileIds = new ArrayList<>();
        IntStream.range(0, width * height).forEach(i -> {
            Tile tile = new Tile(
                    "tile" + i + "-" + boardId, i * 5,
                    new Position(i % width, i / width), new ArrayList<>(), new ArrayList<>(), boardId);
            tileRepository.save(tile);
            tileIds.add(tile.getId());
        });

        Board board = new Board(boardId, width, height, new ArrayList<>(), tileIds);
        boardRepository.save(board);
    }

    @AfterEach
    public void tearDown() {
        mongoTemplate.dropCollection(metadataService.getCollectionName(Tile.class));
        mongoTemplate.dropCollection(metadataService.getCollectionName(Board.class));
    }

    @Test
    public void testFindBySize() {
        List<Board> boards = boardRepository.findBySize(64); // Assuming 8x8 board
        assertThat(boards).hasSize(1);
        assertThat(boards.get(0).getWidth() * boards.get(0).getHeight()).isEqualTo(64);

        boards = boardRepository.findBySize(50); // Assuming 10x5 board
        assertThat(boards).hasSize(1);
        assertThat(boards.get(0).getWidth() * boards.get(0).getHeight()).isEqualTo(50);
    }

    @Test
    public void testBoardAndTilesCreation() {
        Board board = boardRepository.findById("board1").orElse(null);
        assertThat(board).isNotNull();
        assertThat(board.getTileIds()).hasSize(64); // 8x8 board

        List<Tile> tiles = tileRepository.findByBoardId("board1");
        assertThat(tiles).hasSize(64);
        tiles.forEach(tile -> assertThat(tile.getBoardId()).isEqualTo("board1"));
    }

}