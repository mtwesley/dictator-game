package com.mtwesley.dictator.repository;

import com.mtwesley.dictator.model.game.DictatorGame;
import com.mtwesley.dictator.model.game.Game;
import com.mtwesley.dictator.model.player.Player;
import com.mtwesley.dictator.model.game.Game.GameState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerRepository playerRepository;

    @Test
    void testSaveAndRetrieveGame() {
        // Arrange
        Player player = new Player("Player Player");
        Game game = new DictatorGame();

        game.addPlayer(player);

        playerRepository.save(player);
        gameRepository.save(game);

        // Act
        Game retrievedGame = gameRepository.findById(game.getId()).orElse(null);

        // Assert
        assertNotNull(retrievedGame);
        assertEquals(game.getId(), retrievedGame.getId());
    }

    @Test
    void testFindByState() {
        // Arrange
        Game game1 = new DictatorGame();
        Game game2 = new DictatorGame();

        game1.setState(GameState.IN_PROGRESS);
        game2.setState(GameState.COMPLETED);

        gameRepository.save(game1);
        gameRepository.save(game2);

        // Act
        List<Game> inProgressGames = gameRepository.findByState(GameState.IN_PROGRESS);

        // Assert
        assertTrue(inProgressGames.size() > 0);
        assertEquals(GameState.IN_PROGRESS, inProgressGames.get(0).getState());
    }

    @Test
    void testFindByType() {
        // Arrange
        Game game1 = new DictatorGame();

        game1.setType("DICTATOR");

        gameRepository.save(game1);

        // Act
        List<Game> dictatorGames = gameRepository.findByType("DICTATOR");

        // Assert
        assertTrue(dictatorGames.size() > 0);
        assertEquals("DICTATOR", dictatorGames.get(0).getType());
    }

}
