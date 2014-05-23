package be.bendem.chess.pieces;

import be.bendem.chess.Board;
import be.bendem.chess.Color;
import be.bendem.chess.Coordinates;
import be.bendem.chess.Direction;
import be.bendem.chess.Move;

import java.util.Set;

/**
 * @author bendem
 */
public class King extends AbstractPiece {

    public King(Color color, Coordinates coordinates) {
        super(color, coordinates, true);
    }

    @Override
    public boolean canMove(Board board, Move move) {
        if(move.getCount() != 1) {
            return false;
        }

        // TODO Check Rock!

        return super.canMove(board, move);
    }

    @Override
    public Set<Direction> getAllDirections() {
        return Direction.getNormals();
    }

}
