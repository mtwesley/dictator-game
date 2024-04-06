package com.mtwesley.dictator.config;

import com.mtwesley.dictator.model.board.Position;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class MongoConfig {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();

        converters.add((Converter<Position, List<Integer>>) position -> {
            return Arrays.asList(position.getX(), position.getY());
        });

        converters.add((Converter<List<Integer>, Position>) list -> {
            if (list.size() != 2) {
                throw new IllegalArgumentException("Array must have two elements");
            }
            return new Position(list.get(0), list.get(1));
        });

        return new MongoCustomConversions(converters);
    }
}
