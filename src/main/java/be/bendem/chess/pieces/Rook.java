package be.bendem.chess.pieces;

import be.bendem.chess.Board;
import be.bendem.chess.Move;

/**
 * @author bendem
 */
public class Rook extends AbstractPiece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Board board, Move move) {
        return false;
    }

}
