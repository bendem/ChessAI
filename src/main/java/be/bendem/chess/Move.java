package be.bendem.chess;

import be.bendem.chess.pieces.AbstractPiece;

/**
 * @author bendem
 */
public class Move {

    private final AbstractPiece piece;
    private final Coordinates from;
    private final Coordinates to;

    public Move(AbstractPiece piece, Coordinates from, Coordinates to) {
        this.piece = piece;
        this.from = from;
        this.to = to;
    }

    public AbstractPiece getPiece() {
        return piece;
    }

    public Coordinates getFrom() {
        return from;
    }

    public Coordinates getTo() {
        return to;
    }

}
