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

        createAndSaveBoardWithTiles(8, "board1", Board.BoardType.SQUARE);
        createAndSaveBoardWithTiles(10, "board2", Board.BoardType.RECTANGLE);
    }

    private void createAndSaveBoardWithTiles(int size, String boardId, Board.BoardType type) {
        List<String> tileIds = new ArrayList<>();
        IntStream.range(0, size).forEach(i -> {
            Tile tile = new Tile(
                    "tile" + i + "-" + boardId, i * 5,
                    new Position(i % size, i / size), new ArrayList<>(), new ArrayList<>(), boardId);
            tileRepository.save(tile);
            tileIds.add(tile.getId());
        });

        Board board = new Board(boardId, type, size, new ArrayList<>(), tileIds);
        boardRepository.save(board);
    }

    @AfterEach
    public void tearDown() {
        mongoTemplate.dropCollection(metadataService.getCollectionName(Tile.class));
        mongoTemplate.dropCollection(metadataService.getCollectionName(Board.class));
    }

    @Test
    public void testFindByType() {
        List<Board> squareBoards = boardRepository.findByType(Board.BoardType.SQUARE);
        assertThat(squareBoards).hasSize(1);
        assertThat(squareBoards.get(0).getType()).isEqualTo(Board.BoardType.SQUARE);

        List<Board> rectangleBoards = boardRepository.findByType(Board.BoardType.RECTANGLE);
        assertThat(rectangleBoards).hasSize(1);
        assertThat(rectangleBoards.get(0).getType()).isEqualTo(Board.BoardType.RECTANGLE);
    }

    @Test
    public void testBoardAndTilesCreation() {
        Board board = boardRepository.findById("board1").orElse(null);
        assertThat(board).isNotNull();
        assertThat(board.getTileIds()).hasSize(8);

        List<Tile> tiles = tileRepository.findByBoardId("board1");
        assertThat(tiles).hasSize(8);
        tiles.forEach(tile -> assertThat(tile.getBoardId()).isEqualTo("board1"));
    }

}
