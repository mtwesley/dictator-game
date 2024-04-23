package com.mtwesley.dictator.model.board;


import lombok.*;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("tiles")
@TypeAlias("Tile")
public class Tile {

    @Id
    private String id;
    private int coins;
    private Position position;

    private List<String> playerIds;
    private List<String> gameIds;
    private String boardId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Tile)) return false;
        Tile other = (Tile) o;
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
