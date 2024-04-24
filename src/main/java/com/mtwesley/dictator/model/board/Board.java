package com.mtwesley.dictator.model.board;

import com.mtwesley.dictator.model.player.Player;
import lombok.*;
import java.util.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("boards")
@TypeAlias("Board")
public class Board {

    @Id
    private String id;
    private int width;
    private int height;
    private List<String> playerIds;
    private List<String> tileIds;

    public boolean isValidPosition(Position position) {
        return position.getX() >= 0 && position.getX() < width &&
                position.getY() >= 0 && position.getY() < height;
    }

    public int getIndexFromPosition(Position position) {
        return position.getY() * width + position.getX();
    }

    public Position getPositionFromIndex(int index) {
        int x = index % width;
        int y = index / width;
        return new Position(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Board)) return false;
        Board other = (Board) o;
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