package com.mtwesley.dictator.repository;

import com.mtwesley.dictator.model.board.Board;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface BoardRepository extends MongoRepository<Board, String> {

    List<Board> findBySize(int size);

    List<Board> findByType(Board.BoardType type);

    List<Board> findByType(String type);

    @Query("{ 'playerIds': { $size: ?0 } }")
    List<Board> findByNumberOfPlayers(int numberOfPlayers);

    @Query("{ 'playerIds': ?0 }")
    List<Board> findByPlayerId(String playerId);

    @Query("{ 'playerIds': { $in: { ?0 } }")
    List<Board> findByPlayerIds(String playerId);

    @Query("{ 'tileIds': ?0 }")
    List<Board> findByTileId(String tileIds);

    @Query("{ 'tileIds': { $in: ?0 } }")
    List<Board> findByTileIds(List<String> tileIds);

}