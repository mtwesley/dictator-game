package com.mtwesley.dictator.repository;

import com.mtwesley.dictator.model.player.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
    Optional<Player> findByUsername(String username);

    boolean existsByUsername(String username);

    List<Player> findByName(String name);

}
