package be.bendem.chess.pieces;

import be.bendem.chess.Color;
import be.bendem.chess.Position;
import be.bendem.chess.Direction;

import java.util.Set;

/**
 * @author bendem
 */
public class Bishop extends Piece {

    public Bishop(Color color, Position position) {
        super(color, position, false);
    }

    @Override
    public Set<Direction> getAllDirections() {
        return Direction.getDiagonals();
    }

}
