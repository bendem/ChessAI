package be.bendem.chess.pieces;

import be.bendem.chess.Color;
import be.bendem.chess.Coordinates;
import be.bendem.chess.Direction;

import java.util.Set;

/**
 * @author bendem
 */
public class Rook extends AbstractPiece {

    public Rook(Color color, Coordinates coordinates) {
        super(color, coordinates, false);
    }

    @Override
    public Set<Direction> getAllDirections() {
        return Direction.getStraights();
    }

}
