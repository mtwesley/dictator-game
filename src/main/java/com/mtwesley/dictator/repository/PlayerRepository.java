package com.mtwesley.dictator.repository;

import com.mtwesley.dictator.model.player.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {

    List<Player> findByName(String name);

}
