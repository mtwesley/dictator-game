package com.mtwesley.dictator.model.account;

import com.mtwesley.dictator.model.player.Player;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Repository;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document("transactions")
@TypeAlias("Transaction")
public abstract class Transaction {
    protected String id;
    protected String type;

    @DBRef
    @Field("fromAccountId")
    protected Account from;

    @DBRef
    @Field("toAccountId")
    protected Account to;

    protected int amount;
    protected TransactionStatus status = TransactionStatus.PENDING;

    public enum TransactionStatus {
        PENDING,
        ACCEPTED,
        REJECTED
    }

    public Transaction(String type, int amount, Account from, Account to) {
        this.type = type;
        this.amount = amount;
        this.from = from;
        this.to = to;
    }

    public Transaction(String type, int amount, Account from, Account to, TransactionStatus status) {
        this.type = type;
        this.amount = amount;
        this.from = from;
        this.to = to;
        this.status = status;
    }

    public Transaction(String type, int amount, Account from, Account to, String status) {
        this.type = type;
        this.amount = amount;
        this.from = from;
        this.to = to;
        this.status = TransactionStatus.valueOf(status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Transaction)) return false;
        Transaction other = (Transaction) o;
        return id != null && !id.isEmpty() && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return (id != null && !id.isEmpty()) ? id.hashCode() : System.identityHashCode(this);
    }

    @Override
    public String toString() {
        return id;
    }
}