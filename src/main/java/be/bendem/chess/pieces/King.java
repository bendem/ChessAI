package be.bendem.chess.pieces;

import be.bendem.chess.Board;
import be.bendem.chess.Direction;
import be.bendem.chess.Move;

import java.util.Set;

/**
 * @author bendem
 */
public class King extends PieceHandler {

    protected King() {
        super(true);
    }

    @Override
    public boolean canMove(Board board, Move move) {
        // TODO Check Rock!

        return super.canMove(board, move);
    }

    @Override
    public Set<Direction> getAllDirections() {
        return Direction.getNormals();
    }

}
