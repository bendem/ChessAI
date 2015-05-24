package be.bendem.chess.pieces;

import be.bendem.chess.Direction;

import java.util.Set;

/**
 * @author bendem
 */
public class Queen extends PieceHandler {

    protected Queen() {
        super(false);
    }

    @Override
    public Set<Direction> getAllDirections() {
        return Direction.getNormals();
    }

}
