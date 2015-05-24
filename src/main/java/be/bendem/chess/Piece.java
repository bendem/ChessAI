package be.bendem.chess;

import be.bendem.chess.pieces.Type;

public class Piece {

    public final Type type;
    public final Color color;
    private boolean hasMoved;

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
        hasMoved = false;
    }

    public Piece setMoved() {
        hasMoved = true;
        return this;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

}
