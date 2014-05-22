package be.bendem.chess.pieces;

import be.bendem.chess.Board;
import be.bendem.chess.Coordinates;
import be.bendem.chess.Move;

/**
 * @author bendem
 */
public abstract class AbstractPiece {

    protected final Color color;
    protected final Coordinates coordinates;

    protected AbstractPiece(Color color, Coordinates coordinates) {
        this.color = color;
        this.coordinates = coordinates;
    }

    public abstract boolean canMove(Board board, Move move);

    public Coordinates getCoordinates() {
        return coordinates;
    }

}
