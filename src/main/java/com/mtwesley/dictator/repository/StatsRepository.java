package com.mtwesley.dictator.repository;

import com.mtwesley.dictator.model.game.Stats;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatsRepository extends MongoRepository<Stats, String> {

    List<Stats> findByPlayerId(String playerId);

    List<Stats> findByGameId(String gameId);

    @Query("{ 'playerId': ?0, 'isWinner': true }")
    List<Stats> findWinningStatsByPlayerId(String playerId);

    @Query("{ 'playerId': ?0, 'isWinner': false }")
    List<Stats> findLosingStatsByPlayerId(String playerId);

    @Aggregation(pipeline = {
            "{ '$match': { 'playerId': ?0 } }",
            "{ '$group': { '_id': '$playerId', 'totalScore': { '$sum': '$score' } } }"
    })
    int getScoreByPlayerId(String playerId);

    @Aggregation(pipeline = {
            "{ '$match': { 'playerId': ?0, 'isWinner': true } }",
            "{ '$count': 'totalWins' }"
    })
    int getWinsByPlayerId(String playerId);

    @Aggregation(pipeline = {
            "{ '$match': { 'playerId': ?0, 'isWinner': false } }",
            "{ '$count': 'totalLosses' }"
    })
    int getLossesByPlayerId(String playerId);}
