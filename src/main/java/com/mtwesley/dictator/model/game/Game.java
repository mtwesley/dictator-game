package com.mtwesley.dictator.model.game;


import lombok.*;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("games")
@TypeAlias("Game")
public class Game {

    @Id
    private String id;
    private GameType type;
    private GameStatus status;
    private List<GameStats> roles;

    public enum GameType {
        DICTATOR
    }

    public enum GameRole {
        DICTATOR,
        CITIZEN
    }

    public enum GameStatus {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GameStats {
        @Indexed
        private String playerId;
        private GameRole role;
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
