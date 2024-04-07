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

        // Convert from Position to List<Integer> and back using anonymous inner classes
        Converter<Position, List<Integer>> positionToListConverter = new Converter<Position, List<Integer>>() {
            @Override
            public List<Integer> convert(Position source) {
                return Arrays.asList(source.getX(), source.getY());
            }
        };

        Converter<List<Integer>, Position> listToPositionConverter = new Converter<List<Integer>, Position>() {
            @Override
            public Position convert(List<Integer> source) {
                if (source.size() != 2) {
                    throw new IllegalArgumentException("Array must have two elements");
                }
                return new Position(source.get(0), source.get(1));
            }
        };

        converters.add(positionToListConverter);
        converters.add(listToPositionConverter);

        return new MongoCustomConversions(converters);
    }


}
