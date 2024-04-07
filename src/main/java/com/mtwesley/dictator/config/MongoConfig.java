package com.mtwesley.dictator.config;

import com.mtwesley.dictator.model.board.Position;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
public class MongoConfig {
    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new PositionToListConverter());
        converters.add(new ListToPositionConverter());
        return new MongoCustomConversions(converters);
    }

    @WritingConverter
    static class PositionToListConverter implements Converter<Position, List<Integer>> {
        @Override
        public List<Integer> convert(Position source) {
            return Arrays.asList(source.getX(), source.getY());
        }
    }

    @ReadingConverter
    static class ListToPositionConverter implements Converter<List<Integer>, Position> {
        @Override
        public Position convert(List<Integer> source) {
            if (source == null || source.size() != 2) {
                throw new IllegalArgumentException("Array must have two elements");
            }
            return new Position(source.get(0), source.get(1));
        }
    }

}
