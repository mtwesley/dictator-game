package com.mtwesley.dictator.repository;

import com.mtwesley.dictator.model.board.Board;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface BoardRepository extends MongoRepository<Board, String> {

    List<Board> findBySize(int size);

    @Query("{'playerIds': {$size: ?0}}")
    List<Board> findByNumberOfPlayers(int numberOfPlayers);

    @Query("{'playerIds': ?0}")
    List<Board> findByPlayerId(String playerId);

    @Query("{'tiles': {$all: ?0}}")
    List<Board> findByTiles(List<String> tiles);

}