package com.mtwesley.dictator.model.transaction;

import com.mtwesley.dictator.model.player.Player;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("transactions")
@TypeAlias("Transaction")
public class Transaction {

    @Id
    private String id;
    private Player fromPlayerId;
    private Player toPlayerId;
    private int coins;
    private TransactionStatus status;
    private TransactionType type;

    public enum TransactionStatus {
        PENDING,
        ACCEPTED,
        REJECTED
    }

    public enum TransactionType {
        OFFER,
        ENDOWMENT,
        TAX
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