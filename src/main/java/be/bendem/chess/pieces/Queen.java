package be.bendem.chess.pieces;

import be.bendem.chess.Board;
import be.bendem.chess.Coordinates;
import be.bendem.chess.Move;

/**
 * @author bendem
 */
public class Queen extends AbstractPiece {

    public Queen(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    public boolean canMove(Board board, Move move) {
        return false;
    }

}
