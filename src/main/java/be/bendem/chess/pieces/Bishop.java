package be.bendem.chess.pieces;

import be.bendem.chess.Direction;

import java.util.Set;

/**
 * @author bendem
 */
public class Bishop extends PieceHandler {

    protected Bishop() {
        super(false);
    }

    @Override
    public Set<Direction> getAllDirections() {
        return Direction.getDiagonals();
    }

}
