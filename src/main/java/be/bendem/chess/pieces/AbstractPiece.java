package be.bendem.chess.pieces;

import be.bendem.chess.Board;
import be.bendem.chess.Move;

/**
 * @author bendem
 */
public abstract class AbstractPiece {

    protected final Color color;

    protected AbstractPiece(Color color) {
        this.color = color;
    }

    public abstract boolean canMove(Board board, Move move);

}
