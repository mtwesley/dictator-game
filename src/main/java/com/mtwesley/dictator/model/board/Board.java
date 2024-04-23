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
    protected String id;
    protected BoardType type;
    protected int size;
    protected List<String> playerIds;
    protected List<String> tileIds;

    public enum BoardType {
        SQUARE,
        RECTANGLE
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