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
    List<Game> findGamesWonByPlayer(String playerId);

    @Query("{ 'roles.playerId': ?0 }")
    List<Game> findGamesByPlayer(String playerId);

    // Aggregate total score for a player across all games
    // @Query(value = "{ 'scores.?0': { $exists: true } }", fields = "{ 'scores.$': 1 }")
    // List<Game> aggregateScoresForPlayer(String playerId);
}
