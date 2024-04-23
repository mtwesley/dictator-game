package com.mtwesley.dictator.repository;

import com.mtwesley.dictator.model.player.Player;

import static org.assertj.core.api.Assertions.assertThat;

import com.mtwesley.dictator.service.MetadataService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Optional;


@SpringBootTest
public class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private MetadataService metadataService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void setUp() {
        mongoTemplate.dropCollection(metadataService.getCollectionName(Player.class));

        Player player1 = new Player();
        player1.setId("1");
        player1.setUsername("testUser");
        player1.setName("Test User");
        player1.setHash("hashValue123");

        Player player2 = new Player();
        player2.setId("2");
        player2.setUsername("testUser2");
        player2.setName("Test User 2");
        player2.setHash("hashValue456");

        playerRepository.save(player1);
        playerRepository.save(player2);
    }

    @AfterEach
    public void tearDown() {
        mongoTemplate.dropCollection(metadataService.getCollectionName(Player.class));
    }

    @Test
    public void findByUsername_WhenUserExists_ReturnsUser() {
        Optional<Player> result = playerRepository.findByUsername("testUser");
        assertThat(result).isNotEmpty();
        assertThat(result.get().getUsername()).isEqualTo("testUser");
    }

    @Test
    public void findByUsername_WhenUserDoesNotExist_ReturnsEmpty() {
        Optional<Player> result = playerRepository.findByUsername("nonExistingUser");
        assertThat(result).isEmpty();
    }
}

