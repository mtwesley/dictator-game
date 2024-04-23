package com.mtwesley.dictator.repository;

import com.mtwesley.dictator.model.game.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {
    List<Game> findByStatus(Game.GameStatus status);

    List<Game> findByType(Game.GameType type);

    List<Game> findByType(String type);

    @Query("{ 'roles': { $elemMatch: { 'playerId': ?0, 'role': ?1 } } }")
    List<Game> findByPlayerIdAndRole(String playerId, Game.GameStats role);

    @Query("{ 'roles': { $elemMatch: { 'playerId': ?0 } } }")
    List<Game> findByPlayerId(String playerId);

    @Query("{ 'roles': { $elemMatch: { 'playerId': { $in: ?0 } } } }")
    List<Game> findByPlayerIds(Collection<String> playerIds);

    @Query("{ 'roles': { $elemMatch: { 'role': ?0 } } }")
    List<Game> findByRole(Game.GameStats role);

}