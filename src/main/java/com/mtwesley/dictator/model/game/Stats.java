package com.mtwesley.dictator.model.game;

import com.mtwesley.dictator.model.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("stats")
public class Stats {

    @Id
    private String id;

    @DBRef
    private Player player;

    @DBRef
    private Game game;

    private int score;
    private boolean isWinner;

}
