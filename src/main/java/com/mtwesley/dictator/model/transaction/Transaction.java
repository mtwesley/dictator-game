package com.mtwesley.dictator.model.transaction;

import com.mtwesley.dictator.model.player.Player;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("transactions")
@TypeAlias("Transaction")
public abstract class Transaction {
    protected String id;
    protected String type;

    @DBRef
    @Field("fromPlayerId")
    protected Player fromPlayer;

    @DBRef
    @Field("toPlayerId")
    protected Player toPlayer;

    protected int amount;
    protected TransactionStatus status = TransactionStatus.PENDING;

    public enum TransactionStatus {
        PENDING,
        ACCEPTED,
        REJECTED
    }

    public Transaction(String type, int amount, Player fromPlayer, Player toPlayer) {
        this.type = type;
        this.amount = amount;
        this.fromPlayer = fromPlayer;
        this.toPlayer = toPlayer;
    }

    public Transaction(String type, int amount, Player fromPlayer, Player toPlayer, TransactionStatus status) {
        this.type = type;
        this.amount = amount;
        this.fromPlayer = fromPlayer;
        this.toPlayer = toPlayer;
        this.status = status;
    }

    public Transaction(String type, int amount, Player fromPlayer, Player toPlayer, String status) {
        this.type = type;
        this.amount = amount;
        this.fromPlayer = fromPlayer;
        this.toPlayer = toPlayer;
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