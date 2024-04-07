package com.mtwesley.dictator.model.game;

import com.mtwesley.dictator.model.account.Transaction;
import com.mtwesley.dictator.model.player.Player;
import com.mtwesley.dictator.model.player.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.*;


@Getter
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

    public static class ScoreMap extends HashMap<String, Integer> {}
    public static class WinnerMap extends HashMap<String, Boolean> {}

    @Id
    protected String id;
    protected String type;
    protected Turn currentTurn;
    @DBRef
    @Field("players")
    protected Set<Role> roles = new HashSet<>();
    @DBRef
    protected List<Turn> turns = new ArrayList<>();
    @DBRef
    protected List<Transaction> transactions = new ArrayList<>();
    protected ScoreMap scores = new ScoreMap();
    protected WinnerMap winners = new WinnerMap();
    protected GameState state = GameState.STARTING;

    public abstract Comparator<Role> getRoleComparator();
    public abstract Turn nextTurn();

    public abstract Role assignRole(Player player); // also first check if player is already in game, and assign the correct role
    public abstract int calculateScore(Player player);

    public void addPlayer(Player player) {}

    public void removePlayer(Player player) {}

    public boolean isWinner(Player player) {
        if (state != GameState.COMPLETED || scores == null || scores.isEmpty()) {
            return false;
        }

        int score = calculateScore(player);
        int maxScore = Collections.max(scores.values());

        return score > 0 && score == maxScore;
    }

}
