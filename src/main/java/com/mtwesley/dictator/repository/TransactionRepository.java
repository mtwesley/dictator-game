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

    @Query("{ '$or': [ {'fromAccountId.$id': ?0 }, {'toAccountId.$id': ?1 } ] }")
    List<Transaction> findByFromOrToAccountId(String fromAccountId, String toAccountId);

    @Query("{ '$or': [ {'fromAccountId.$id': ?0 }, {'toAccountId.$id': ?1 } ], '_class': 'Offer' }")
    List<Offer> findOffersByFromOrToAccountId(String fromAccountId, String toAccountId);

    @Query("{ 'fromAccountId.$id': ?0 }")
    List<Transaction> findByFromAccountId(String accountId);

    @Query("{ 'toAccountId.$id': ?0 }")
    List<Transaction> findByToAccountId(String accountId);

    @Query("{ 'from.id': ?0, '_class': 'Offer' }")
    List<Offer> findOffersByFromAccountId(String accountId);

    @Query("{ 'to.id': ?0, '_class': 'Offer' }")
    List<Offer> findOffersByToAccountId(String accountId);

    @Query("{ 'to.id': ?0, '_class': 'Endowment' }")
    List<Endowment> findEndowmentsByToAccountId(String accountId);

    @Query("{ 'from.id': ?0, '_class': 'Tax' }")
    List<Tax> findTaxesByFromAccountId(String accountId);

    @Aggregation(pipeline = {
            "{ '$match': { 'fromAccountId.$id': ?0 } }",
            "{ '$group': { '_id': null, 'total': { '$sum': '$amount' } } }"
    })
    int getAmountByFromAccountId(String accountId);

    @Aggregation(pipeline = {
            "{ '$match': { 'toAccountId.$id': ?0 } }",
            "{ '$group': { '_id': null, 'total': { '$sum': '$amount' } } }"
    })
    int getAmountByToAccountId(String accountId);

    @Aggregation(pipeline = {
            "{ '$match': { 'fromAccountId.$id': ?0, '_class': 'Offer' } }",
            "{ '$group': { '_id': null, 'total': { '$sum': '$amount' } } }"
    })
    int getOffersAmountByFromAccountId(String accountId);

    @Aggregation(pipeline = {
            "{ '$match': { 'toAccountId.$id': ?0, '_class': 'Offer' } }",
            "{ '$group': { '_id': null, 'total': { '$sum': '$amount' } } }"
    })
    int getOffersAmountByToAccountId(String accountId);

    @Aggregation(pipeline = {
            "{ '$match': { 'toAccountId.$id': ?0, '_class': 'Endowment' } }",
            "{ '$group': { '_id': null, 'total': { '$sum': '$amount' } } }"
    })
    int getEndowmentsAmountByToAccountId(String accountId);

    @Aggregation(pipeline = {
            "{ '$match': { 'fromAccountId.$id': ?0, '_class': 'Tax' } }",
            "{ '$group': { '_id': null, 'total': { '$sum': '$amount' } } }"
    })
    int getTaxesAmountByFromAccountId(String accountId);

    List<Transaction> findByType(String type);

    default List<Transaction> findByAccountId(String accountId) {
        return findByFromOrToAccountId(accountId, accountId);
    }

    default int getBalanceByAccountId(String accountId) {
        return getAmountByToAccountId(accountId) - getAmountByFromAccountId(accountId);
    }
}
