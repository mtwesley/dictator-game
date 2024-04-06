package com.mtwesley.dictator.repository;

import com.mtwesley.dictator.model.account.Endowment;
import com.mtwesley.dictator.model.account.Offer;
import com.mtwesley.dictator.model.account.Tax;
import com.mtwesley.dictator.model.account.Transaction;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    @Query("{ '$or': [ {'from.$id': ?0 }, {'to.$id': ?1 } ] }")
    List<Transaction> findFromOrToAccountId(String fromAccountId, String toAccountId);

    @Query("{ '$or': [ {'from.$id': ?0 }, {'to.$id': ?1 } ], '_class': 'Offer' }")
    List<Offer> findOffersFromOrToAccountId(String fromAccountId, String toAccountId);

    @Query("{ 'from.$id': ?0 }")
    List<Transaction> findFromAccountId(String accountId);

    @Query("{ 'to.$id': ?0 }")
    List<Transaction> findToAccountId(String accountId);

    @Query("{ 'from.id': ?0, '_class': 'Offer' }")
    List<Offer> findOffersFromAccountId(String accountId);

    @Query("{ 'to.id': ?0, '_class': 'Offer' }")
    List<Offer> findOffersToAccountId(String accountId);

    @Query("{ 'to.id': ?0, '_class': 'Endowment' }")
    List<Endowment> findEndowmentsToAccountId(String accountId);

    @Query("{ 'from.id': ?0, '_class': 'Tax' }")
    List<Tax> findTaxesFromAccountId(String accountId);

    @Aggregation(pipeline = {
            "{ '$match': { 'from.$id': ?0 } }",
            "{ '$group': { '_id': null, 'total': { '$sum': '$amount' } } }"
    })
    int getAmountFromAccountId(String accountId);

    @Aggregation(pipeline = {
            "{ '$match': { 'to.$id': ?0 } }",
            "{ '$group': { '_id': null, 'total': { '$sum': '$amount' } } }"
    })
    int getAmountToAccountId(String accountId);

    @Aggregation(pipeline = {
            "{ '$match': { 'from.$id': ?0, '_class': 'Offer' } }",
            "{ '$group': { '_id': null, 'total': { '$sum': '$amount' } } }"
    })
    int getOffersAmountFromAccountId(String accountId);

    @Aggregation(pipeline = {
            "{ '$match': { 'to.$id': ?0, '_class': 'Offer' } }",
            "{ '$group': { '_id': null, 'total': { '$sum': '$amount' } } }"
    })
    int getOffersAmountToAccountId(String accountId);

    @Aggregation(pipeline = {
            "{ '$match': { 'to.$id': ?0, '_class': 'Endowment' } }",
            "{ '$group': { '_id': null, 'total': { '$sum': '$amount' } } }"
    })
    int getEndowmentsAmountToAccountId(String accountId);

    @Aggregation(pipeline = {
            "{ '$match': { 'from.$id': ?0, '_class': 'Tax' } }",
            "{ '$group': { '_id': null, 'total': { '$sum': '$amount' } } }"
    })
    int getTaxesAmountFromAccountId(String accountId);

    List<Transaction> findByType(String type);

    default List<Transaction> findByAccountId(String accountId) {
        return findFromOrToAccountId(accountId, accountId);
    }

    default int getBalanceByAccountId(String accountId) {
        return getAmountToAccountId(accountId) - getAmountFromAccountId(accountId);
    }
}
