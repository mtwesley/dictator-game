package com.mtwesley.dictator.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mtwesley.dictator.model.board.Board;
import com.mtwesley.dictator.model.board.Tile;
import com.mtwesley.dictator.service.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class BoardControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BoardService boardService;

    @InjectMocks
    private BoardController boardController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
    }

    @Test
    public void getDefaultBoard_BoardExists_ReturnsBoard() throws Exception {
        Board board = new Board(); // Assume Board has necessary fields set up
        when(boardService.getDefaultBoard()).thenReturn(board);

        mockMvc.perform(get("/boards/default"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(board.getId()));

        verify(boardService).getDefaultBoard();
    }

    @Test
    public void getDefaultBoard_NoBoardExists_ReturnsNotFound() throws Exception {
        when(boardService.getDefaultBoard()).thenReturn(null);

        mockMvc.perform(get("/boards/default"))
                .andExpect(status().isNotFound());

        verify(boardService).getDefaultBoard();
    }

    @Test
    public void getBoardById_BoardExists_ReturnsBoard() throws Exception {
        String boardId = "someBoardId";
        Board board = new Board(); // Assume Board has necessary fields set up
        when(boardService.getBoardById(boardId)).thenReturn(board);

        mockMvc.perform(get("/boards/{boardId}", boardId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(board.getId()));

        verify(boardService).getBoardById(boardId);
    }

    @Test
    public void getBoardById_NoBoardExists_ReturnsNotFound() throws Exception {
        String boardId = "someBoardId";
        when(boardService.getBoardById(boardId)).thenReturn(null);

        mockMvc.perform(get("/boards/{boardId}", boardId))
                .andExpect(status().isNotFound());

        verify(boardService).getBoardById(boardId);
    }

    @Test
    public void getAllTilesFromBoard_TilesExist_ReturnsTiles() throws Exception {
        String boardId = "boardId";
        List<Tile> tiles = Arrays.asList(new Tile(), new Tile()); // Assume Tile has necessary fields set up
        when(boardService.getTilesByBoardId(boardId)).thenReturn(tiles);

        mockMvc.perform(get("/boards/{boardId}/tiles", boardId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(tiles.size()));

        verify(boardService).getTilesByBoardId(boardId);
    }

    @Test
    public void getAllTilesFromBoard_NoTilesExist_ReturnsNotFound() throws Exception {
        String boardId = "boardId";
        when(boardService.getTilesByBoardId(boardId)).thenReturn(Arrays.asList());

        mockMvc.perform(get("/boards/{boardId}/tiles", boardId))
                .andExpect(status().isNotFound());

        verify(boardService).getTilesByBoardId(boardId);
    }
}
