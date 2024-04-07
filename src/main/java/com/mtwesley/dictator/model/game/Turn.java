package com.mtwesley.dictator.model.game;

import com.mtwesley.dictator.model.action.Action;
import com.mtwesley.dictator.model.player.Player;
import com.mtwesley.dictator.model.player.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("turns")
@TypeAlias("Turn")
public abstract class Turn {

    @Id
    String id;
    @DBRef
    protected Role role;
    protected List<Action> actions;

    protected LocalDateTime start;
    protected LocalDateTime end;

    public void startTurn() {
        this.start = LocalDateTime.now();
    }

    public void endTurn() {
        this.end = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Turn)) return false;
        Turn other = (Turn) o;
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