package com.mtwesley.dictator.model.game;


import lombok.*;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
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
    private List<String> playerIds;

    public enum GameType {
        DICTATOR
    }

    public enum GameStatus {
        PENDING,
        IN_PROGRESS,
        COMPLETED
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
