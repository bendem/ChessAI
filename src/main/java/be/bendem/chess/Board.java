package be.bendem.chess;

import java.util.stream.Stream;

public interface Board {

    int WIDTH = 8;
    int HEIGHT = 8;

    Piece get(Position position);

    Position getPosition(Piece piece);

    boolean isEmpty(Position position);

    Stream<Piece> stream();

    Board move(Move move);

    Board snapshot();

}
