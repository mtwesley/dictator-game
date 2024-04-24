package com.mtwesley.dictator.repository;

import com.mtwesley.dictator.model.board.Board;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface BoardRepository extends MongoRepository<Board, String> {

    @Query("{ $expr: { $eq: [ { $multiply: ['$width', '$height'] }, ?0 ] } }")
    List<Board> findBySize(int size);

    @Query("{ $expr: { $gte: [ { $multiply: ['$width', '$height'] }, ?0 ] } }")
    List<Board> findBySizeGreaterThanEqual(int size);

    @Query("{ $expr: { $lte: [ { $multiply: ['$width', '$height'] }, ?0 ] } }")
    List<Board> findBySizeLessThanEqual(int size);

    @Query("{ 'playerIds': { $size: ?0 } }")
    List<Board> findByNumberOfPlayers(int number);

    @Query("{ 'playerIds': ?0 }")
    List<Board> findByPlayerId(String playerId);

    @Query("{ 'playerIds': { $in: [?0] } }")
    List<Board> findByPlayerIds(List<String> playerIds);

    @Query("{ 'tileIds': ?0 }")
    List<Board> findByTileId(String tileId);

    @Query("{ 'tileIds': { $in: [?0] } }")
    List<Board> findByTileIds(List<String> tileIds);
}