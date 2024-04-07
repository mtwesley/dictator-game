package com.mtwesley.dictator.model.player;

import com.mtwesley.dictator.model.account.Account;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document("players")
@TypeAlias("Player")
public class Player implements Account {
    @Id
    private String id;
    private String name;
    @Field("cachedWins")
    private int wins = 0;
    @Field("cachedBalance")
    private int balance = 0;
    @Field("cachedScore")
    private int score = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Player)) return false;
        Player other = (Player) o;
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
