package com.mtwesley.dictator.model.player;

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
    private Board board;
    private Position position;
    private int wins;
    private int losses;
    private int balance;
    private int score;

    public void move(Direction direction) {
        System.out.println(name + " moves " + direction + " to a new position.");
    }
}
