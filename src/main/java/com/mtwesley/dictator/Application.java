package com.mtwesley.dictator;

import com.mtwesley.dictator.model.board.Board;
import com.mtwesley.dictator.model.board.SquareBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.PostConstruct;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}