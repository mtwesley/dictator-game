package com.mtwesley.dictator.repository;

import com.mtwesley.dictator.model.transaction.Endowment;
import com.mtwesley.dictator.model.transaction.Offer;
import com.mtwesley.dictator.model.transaction.Tax;
import com.mtwesley.dictator.model.transaction.Transaction;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    @Query("{ '$or': [ {'fromPlayerId.$id': ?0 }, {'toPlayerId.$id': ?1 } ] }")
    List<Transaction> findByFromOrToPlayerId(String fromPlayerId, String toPlayerId);

    @Query("{ '$or': [ {'fromPlayerId.$id': ?0 }, {'toPlayerId.$id': ?1 } ], '_class': 'Offer' }")
    List<Offer> findOffersByFromOrToPlayerId(String fromPlayerId, String toPlayerId);

    @Query("{ 'fromPlayerId.$id': ?0 }")
    List<Transaction> findByFromPlayerId(String playerId);

    @Query("{ 'toPlayerId.$id': ?0 }")
    List<Transaction> findByToPlayerId(String playerId);

    @Query("{ 'from.id': ?0, '_class': 'Offer' }")
    List<Offer> findOffersByFromPlayerId(String playerId);

    @Query("{ 'to.id': ?0, '_class': 'Offer' }")
    List<Offer> findOffersByToPlayerId(String playerId);

    @Query("{ 'to.id': ?0, '_class': 'Endowment' }")
    List<Endowment> findEndowmentsByToPlayerId(String playerId);

    @Query("{ 'from.id': ?0, '_class': 'Tax' }")
    List<Tax> findTaxesByFromPlayerId(String playerId);

    @Aggregation(pipeline = {
            "{ '$match': { 'fromPlayerId.$id': ?0 } }",
            "{ '$group': { '_id': null, 'total': { '$sum': '$amount' } } }"
    })
    int getAmountByFromPlayerId(String playerId);

    @Aggregation(pipeline = {
            "{ '$match': { 'toPlayerId.$id': ?0 } }",
            "{ '$group': { '_id': null, 'total': { '$sum': '$amount' } } }"
    })
    int getAmountByToPlayerId(String playerId);

    @Aggregation(pipeline = {
            "{ '$match': { 'fromPlayerId.$id': ?0, '_class': 'Offer' } }",
            "{ '$group': { '_id': null, 'total': { '$sum': '$amount' } } }"
    })
    int getOffersAmountByFromPlayerId(String playerId);

    @Aggregation(pipeline = {
            "{ '$match': { 'toPlayerId.$id': ?0, '_class': 'Offer' } }",
            "{ '$group': { '_id': null, 'total': { '$sum': '$amount' } } }"
    })
    int getOffersAmountByToPlayerId(String playerId);

    @Aggregation(pipeline = {
            "{ '$match': { 'toPlayerId.$id': ?0, '_class': 'Endowment' } }",
            "{ '$group': { '_id': null, 'total': { '$sum': '$amount' } } }"
    })
    int getEndowmentsAmountByToPlayerId(String playerId);

    @Aggregation(pipeline = {
            "{ '$match': { 'fromPlayerId.$id': ?0, '_class': 'Tax' } }",
            "{ '$group': { '_id': null, 'total': { '$sum': '$amount' } } }"
    })
    int getTaxesAmountByFromPlayerId(String playerId);

    List<Transaction> findByType(String type);

    default List<Transaction> findByPlayerId(String playerId) {
        return findByFromOrToPlayerId(playerId, playerId);
    }

    default int getBalanceByPlayerId(String playerId) {
        return getAmountByToPlayerId(playerId) - getAmountByFromPlayerId(playerId);
    }
}
