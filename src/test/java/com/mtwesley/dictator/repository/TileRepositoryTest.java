package com.mtwesley.dictator.repository;

import com.mtwesley.dictator.model.board.Position;
import com.mtwesley.dictator.model.board.Tile;
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
public class TileRepositoryTest {

    @Autowired
    private TileRepository tileRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MetadataService metadataService;

    @BeforeEach
    public void setUp() {
        mongoTemplate.dropCollection(metadataService.getCollectionName(Tile.class));

        Tile tile1 = new Tile("tile1", 5, new Position(0, 0), Arrays.asList("player1"), Arrays.asList("game1"), "board1");
        Tile tile2 = new Tile("tile2", 10, new Position(1, 1), Arrays.asList("player2"), Arrays.asList("game2"), "board2");
        Tile tile3 = new Tile("tile3", 15, new Position(2, 2), Arrays.asList("player1", "player3"), Arrays.asList("game3", "game4"), "board3");

        tileRepository.saveAll(Arrays.asList(tile1, tile2, tile3));
    }

    @AfterEach
    public void tearDown() {
        mongoTemplate.dropCollection(metadataService.getCollectionName(Tile.class));
    }

    @Test
    public void findByBoardId_WhenTilesExist_ReturnsCorrectTiles() {
        List<Tile> results = tileRepository.findByBoardId("board1");
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getBoardId()).isEqualTo("board1");
    }

    @Test
    public void findByCoins_WhenTilesExist_ReturnsCorrectTiles() {
        List<Tile> results = tileRepository.findByCoins(10);
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getCoins()).isEqualTo(10);
    }

    @Test
    public void findByPosition_WhenTilesExist_ReturnsCorrectTiles() {
        Position searchPosition = new Position(1, 1);
        List<Tile> results = tileRepository.findByPosition(searchPosition);
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getPosition().getX()).isEqualTo(searchPosition.getX());
        assertThat(results.get(0).getPosition().getY()).isEqualTo(searchPosition.getY());
    }

    @Test
    public void findByPlayerId_WhenTilesExist_ReturnsCorrectTiles() {
        List<Tile> results = tileRepository.findByPlayerId("player1");
        assertThat(results).isNotEmpty();
        assertThat(results.stream().anyMatch(t -> t.getPlayerIds().contains("player1"))).isTrue();
    }

    @Test
    public void findByGameId_WhenTilesExist_ReturnsCorrectTiles() {
        List<Tile> results = tileRepository.findByGameId("game3");
        assertThat(results).isNotEmpty();
        assertThat(results.stream().anyMatch(t -> t.getGameIds().contains("game3"))).isTrue();
    }
}
