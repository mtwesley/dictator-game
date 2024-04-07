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

//    @WritingConverter
//    public static class TileToDocumentConverter implements Converter<Tile, Document> {
//        @Override
//        public Document convert(Tile tile) {
//            Document doc = new Document();
//            doc.append("position", Arrays.asList(tile.getPosition().getX(), tile.getPosition().getY()));
//            // Include other Tile fields as needed
//            return doc;
//        }
//    }

//    @ReadingConverter
//    public static class DocumentToTileConverter implements Converter<Document, Tile> {
//        @Override
//        public Tile convert(Document doc) {
//            List<Integer> positionList = doc.getList("position", Integer.class);
//            Position position = new Position(positionList.get(0), positionList.get(1));
//            // Construct and return a new Tile object using the retrieved position and other properties
//            return new Tile(null, position); // Assuming 'null' for Board, replace with actual value as needed
//        }
//    }

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
