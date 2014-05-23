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
public class Bishop extends AbstractPiece {

    public Bishop(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    public boolean canMove(Board board, Move move) {
        return false;
    }

    @Override
    public Set<Direction> getAllDirections() {
        return Direction.getDiagonals();
    }

}
