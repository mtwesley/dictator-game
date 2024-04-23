package com.mtwesley.dictator.repository;

import com.mtwesley.dictator.model.board.Position;
import com.mtwesley.dictator.model.board.Tile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TileRepository extends MongoRepository<Tile, String> {

    List<Tile> findByBoardId(String boardId);

    List<Tile> findByCoins(int coins);

    @Query("{ 'position': ?0 }")
    List<Tile> findByPosition(Position position);

    @Query("{ 'playerIds': ?0 }")
    List<Tile> findByPlayerId(String playerId);

    @Query("{ 'gameIds': { $in: ?0 } }")
    List<Tile> findByGameId(String gameId);

}