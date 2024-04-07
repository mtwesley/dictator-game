package com.mtwesley.dictator.repository;

import com.mtwesley.dictator.model.game.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {

    List<Game> findByState(Game.GameState state);

    List<Game> findByType(String type);

    @Query("{ 'winners.?0': true }")
    List<Game> findByWinningPlayerId(String playerId);

    @Query("{ 'roles.playerId': ?0 }")
    List<Game> findByPlayerId(String playerId);

}
