package com.mtwesley.dictator.model.player;

import com.mtwesley.dictator.model.account.Account;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document("players")
@TypeAlias("Player")
public class Player implements Account {
    @Id
    private String id;
    private String name;
    private int wins = 0;
    private int balance = 0;
    private int score = 0;

}
