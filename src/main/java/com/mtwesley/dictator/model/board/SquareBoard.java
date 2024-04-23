package com.mtwesley.dictator.model.board;


import lombok.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;


//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class SquareBoard extends Board {
//    private int width;
//    private int height;
//
//    public SquareBoard(int width, int height, int maxPlayersPerTile) {
//        super(width * height, maxPlayersPerTile);
//        this.width = width;
//        this.height = height;
//        initializeTiles();
//    }
//
//    @Override
//    protected void initializeTiles() {
//        for (int i = 0; i < size; i++) {
//            tiles.add(new Tile(id, new Position(i % width, i / width)));
//        }
//    }
//
//    @Override
//    public int getIndexFromPosition(Position position) {
//        return position.getY() * width + position.getX();
//    }
//
//    @Override
//    public Position getPositionFromIndex(int index) {
//        if (index < 0 || index >= size) {
//            throw new IllegalArgumentException("Index out of board bounds");
//        }
//        int x = index % width;
//        int y = index / width;
//        return new Position(x, y);
//    }
//
//    @Override
//    protected boolean isValidPosition(Position position) {
//        return position.getX() >= 0 && position.getX() < width &&
//                position.getY() >= 0 && position.getY() < height;
//    }
//
//    @Override
//    protected Position move(Position position, Direction direction) {
//        int newX = position.getX();
//        int newY = position.getY();
//
//        switch (direction) {
//            case UP: newY--; break;
//            case DOWN: newY++; break;
//            case LEFT: newX--; break;
//            case RIGHT: newX++; break;
//        }
//
//        Position newPosition = new Position(newX, newY);
//        if (isValidPosition(newPosition)) {
//            return newPosition;
//        }
//        return null;
//    }
}
