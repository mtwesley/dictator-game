package com.mtwesley.dictator.repository;

import com.mtwesley.dictator.model.board.Board;
import com.mtwesley.dictator.model.board.SquareBoard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testCreateAndFindBoard() {
        // Create and save a board
        Board board = new SquareBoard(10, 10, 4);
        mongoTemplate.save(board);

        // Retrieve the board
        Board foundBoard = mongoTemplate.findById(board.getId(), Board.class);
        assertThat(foundBoard).isNotNull();
        assertThat(foundBoard.getTiles()).hasSize(100);

    }
}

