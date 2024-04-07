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

    @Aggregation(pipeline = {
            "{ '$match': { 'players._id': ?0 } }",
            "{ '$group': { '_id': null, 'totalScore': { '$sum': '$scores.$playerId' } } }"
    })
    int sumScoreByPlayerId(String playerId);

    // Method to count the number of wins for a player
    @Aggregation(pipeline = {
            "{ '$match': { 'winners': ?0 } }",
            "{ '$count': 'totalWins' }"
    })
    int countWinsByPlayerId(String playerId);


    // Aggregate total score for a player across all games
    // @Query(value = "{ 'scores.?0': { $exists: true } }", fields = "{ 'scores.$': 1 }")
    // List<Game> aggregateScoresForPlayer(String playerId);
}
