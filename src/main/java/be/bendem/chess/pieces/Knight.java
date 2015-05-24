package be.bendem.chess.pieces;

import be.bendem.chess.Direction;

import java.util.Set;

/**
 * @author bendem
 */
public class Knight extends PieceHandler {

    protected Knight() {
        super(true);
    }

    @Override
    public Set<Direction> getAllDirections() {
        return Direction.getKnights();
    }

}
