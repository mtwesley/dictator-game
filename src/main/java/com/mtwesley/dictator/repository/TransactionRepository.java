package com.mtwesley.dictator.repository;

import java.util.List;
import com.mtwesley.dictator.model.transaction.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Transaction> findByStatus(Transaction.TransactionStatus status);

    List<Transaction> findByStatus(String status);

    List<Transaction> findByType(Transaction.TransactionType type);

    List<Transaction> findByType(String type);

    @Query("{$or: [{'fromPlayerId': ?0}, {'toPlayerId': ?0}]}")
    List<Transaction> findByPlayerId(String playerId);

    @Query("{'fromPlayerId': ?0}")
    List<Transaction> findByFromPlayerId(String fromPlayerId);

    @Query("{'toPlayerId': ?0}")
    List<Transaction> findByToPlayerId(String toPlayerId);

    @Query("{$and: [{'fromPlayerId': ?0}, {'toPlayerId': ?1}]}")
    List<Transaction> findTransactionsBetweenPlayers(String fromPlayerId, String toPlayerId);

    @Query("{$and: [{'type': ?0}, {'status': ?1}]}")
    List<Transaction> findByTypeAndStatus(Transaction.TransactionType type, Transaction.TransactionStatus status);

    @Query("{$and: [{'type': ?0}, {'status': ?1}]}")
    List<Transaction> findByTypeAndStatus(String type, String status);

}
