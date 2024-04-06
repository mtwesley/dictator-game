package com.mtwesley.dictator.model.board;

import lombok.Value;
import org.springframework.data.annotation.TypeAlias;

@Value
public class Position {
    int x;
    int y;

}
