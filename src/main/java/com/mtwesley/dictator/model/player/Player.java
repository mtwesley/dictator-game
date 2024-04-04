package com.mtwesley.dictator.model.player;

import com.mtwesley.dictator.model.account.Account;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Player implements Account {
    private String id;
    private String name;
    private int wins;
    private int losses;
    private int balance;
    private int score;
}
