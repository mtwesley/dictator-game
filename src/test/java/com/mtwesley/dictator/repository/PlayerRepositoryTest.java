package com.mtwesley.dictator.repository;

import com.mtwesley.dictator.model.player.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PlayerRepositoryTest {

//    @Autowired
//    private PlayerRepository playerRepository;
//
//    @Test
//    public void testSaveAndRetrievePlayer() {
//        Player player = new Player("Normal Player");
//        playerRepository.save(player);
//
//        Player foundPlayer = playerRepository.findById(player.getId()).orElse(null);
//        assertThat(foundPlayer).isInstanceOf(Player.class);
//        assertThat(foundPlayer.getName()).isEqualTo("Normal Player");
//    }
//
//    @Test
//    public void testSaveAndRetrieveDictator() {
//        Player basePlayer = new Player("Dictator Player");
//        Dictator dictator = new Dictator(basePlayer);
//        playerRepository.save(dictator);
//
//        Player foundPlayer = playerRepository.findById(dictator.getId()).orElse(null);
//        assertThat(foundPlayer).isInstanceOf(Dictator.class);
//        assertThat(foundPlayer.getName()).isEqualTo("Dictator Player");
//    }
//
//    @Test
//    public void testSaveAndRetrieveCitizen() {
//        Player basePlayer = new Player("Citizen Player");
//        Citizen citizen = new Citizen(basePlayer);
//        playerRepository.save(citizen);
//
//        Player foundPlayer = playerRepository.findById(citizen.getId()).orElse(null);
//        assertThat(foundPlayer).isInstanceOf(Citizen.class);
//        assertThat(foundPlayer.getName()).isEqualTo("Citizen Player");
//    }

}
