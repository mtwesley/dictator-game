package com.mtwesley.dictator.model.player;

import com.mtwesley.dictator.model.game.board.Position;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Player {
    private String id;
    private String name;
    private int wins;
    private int losses;
    private int balance;
    private int score;

}
