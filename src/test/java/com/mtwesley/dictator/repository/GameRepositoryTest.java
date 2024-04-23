package com.mtwesley.dictator.repository;

import com.mtwesley.dictator.model.game.Game;
import com.mtwesley.dictator.model.player.Player;
import com.mtwesley.dictator.service.MetadataService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private MetadataService metadataService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void setUp() {
        // Clean up both collections to ensure tests are isolated
        mongoTemplate.dropCollection(metadataService.getCollectionName(Game.class));
        mongoTemplate.dropCollection(metadataService.getCollectionName(Player.class));

        // Create player entities that will be referenced in the Game documents
        Player player1 = new Player("player1", "Player One", "username1", "hash1");
        Player player2 = new Player("player2", "Player Two", "username2", "hash2");
        Player player3 = new Player("player3", "Player Three", "username3", "hash3");
        Player player4 = new Player("player4", "Player Four", "username4", "hash4");

        playerRepository.saveAll(Arrays.asList(player1, player2, player3, player4));

        // Prepare test data for games
        Game game1 = new Game();
        game1.setId("game1");
        game1.setType(Game.GameType.DICTATOR);
        game1.setStatus(Game.GameStatus.IN_PROGRESS);
        game1.setRoles(Arrays.asList(
                new Game.GameStats(player1.getId(), Game.GameRole.DICTATOR),
                new Game.GameStats(player2.getId(), Game.GameRole.CITIZEN)
        ));

        Game game2 = new Game();
        game2.setId("game2");
        game2.setType(Game.GameType.DICTATOR);
        game2.setStatus(Game.GameStatus.COMPLETED);
        game2.setRoles(Arrays.asList(
                new Game.GameStats(player3.getId(), Game.GameRole.DICTATOR),
                new Game.GameStats(player4.getId(), Game.GameRole.CITIZEN)
        ));

        gameRepository.saveAll(Arrays.asList(game1, game2));
    }

    @AfterEach
    public void tearDown() {
        // Clean up after each test
        mongoTemplate.dropCollection(metadataService.getCollectionName(Game.class));
        mongoTemplate.dropCollection(metadataService.getCollectionName(Player.class));
    }

    @Test
    public void findByStatus_WhenGamesExist_ReturnsGames() {
        List<Game> results = gameRepository.findByStatus(Game.GameStatus.IN_PROGRESS);
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getStatus()).isEqualTo(Game.GameStatus.IN_PROGRESS);
    }

    @Test
    public void findByPlayerId_WhenPlayerExists_ReturnsGames() {
        List<Game> results = gameRepository.findByPlayerId("player1");
        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getRoles().stream().anyMatch(role -> role.getPlayerId().equals("player1"))).isTrue();
    }
}