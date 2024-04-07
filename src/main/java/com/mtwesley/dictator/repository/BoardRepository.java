package com.mtwesley.dictator.repository;

import com.mtwesley.dictator.model.board.Board;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends MongoRepository<Board, String> {

    @Query("{ 'type': ?0 }")
    List<Board> findByType(String type);

}