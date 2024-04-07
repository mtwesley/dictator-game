package com.mtwesley.dictator.model.game;

import com.mtwesley.dictator.model.transaction.Transaction;
import com.mtwesley.dictator.model.player.Player;
import com.mtwesley.dictator.model.player.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("games")
@TypeAlias("Game")
public abstract class Game {

    public enum GameState {
        STARTING,
        IN_PROGRESS,
        ENDING,
        COMPLETED
    }

    @Id
    protected String id;
    protected String type;
    protected Turn currentTurn;
    @DBRef
    protected Set<Player> players = new HashSet<>();
    @DBRef
    protected List<Turn> turns = new ArrayList<>();
    @DBRef
    protected List<Transaction> transactions = new ArrayList<>();
    @DBRef
    protected List<Stats> stats = new ArrayList<>();
    protected GameState state = GameState.STARTING;

    public abstract Comparator<Role> getRoleComparator();
    public abstract Turn nextTurn();

    public abstract Role assignRole(Player player);
    public abstract int calculateScore(Player player);

    public boolean addPlayer(Player player) {
        if (players.stream().anyMatch(p -> p.equals(player))) {
            return false;
        }

        Role newRole = assignRole(player);
        if (newRole != null) {
            players.add(newRole);
            return true;
        }

        return false;
    }

    public boolean removePlayer(Player player) {
        return players.removeIf(p -> p.equals(player));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Game)) return false;
        Game other = (Game) o;
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
