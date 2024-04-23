package com.mtwesley.dictator.repository;

import com.mtwesley.dictator.model.board.Board;
import com.mtwesley.dictator.service.MetadataService;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MetadataService metadataService;

    @BeforeEach
    public void setUp() {
        mongoTemplate.dropCollection(metadataService.getCollectionName(Board.class));

        Board board1 = new Board("board1", Board.BoardType.RECTANGLE, 8, Arrays.asList("player1"), Arrays.asList("tile1", "tile2"));
        Board board2 = new Board("board2", Board.BoardType.SQUARE, 10, Arrays.asList("player3", "player4"), Arrays.asList("tile3", "tile4"));

        boardRepository.saveAll(Arrays.asList(board1, board2));
    }

    @AfterEach
    public void tearDown() {
        mongoTemplate.dropCollection(metadataService.getCollectionName(Board.class));
    }

    @Test
    public void findBySize_WhenBoardsExist_ReturnsCorrectBoards() {
        List<Board> results = boardRepository.findBySize(8);
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getSize()).isEqualTo(8);
    }

    @Test
    public void findByNumberOfPlayers_WhenBoardsExist_ReturnsCorrectBoards() {
        List<Board> results = boardRepository.findByNumberOfPlayers(2);
        assertThat(results).isNotEmpty();
        assertThat(results.size()).isEqualTo(1);
    }
}
