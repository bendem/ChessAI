package be.bendem.chess.pieces;

import be.bendem.chess.Direction;

import java.util.Set;

/**
 * @author bendem
 */
public class Rook extends PieceHandler {

    protected Rook() {
        super(false);
    }

    @Override
    public Set<Direction> getAllDirections() {
        return Direction.getStraights();
    }

}
