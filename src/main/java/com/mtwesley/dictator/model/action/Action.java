package com.mtwesley.dictator.model.action;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Action {

    protected String id;
    protected String action;
    protected String playerId;

}