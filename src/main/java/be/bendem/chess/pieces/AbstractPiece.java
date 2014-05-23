package be.bendem.chess.pieces;

import be.bendem.chess.Board;
import be.bendem.chess.Color;
import be.bendem.chess.Coordinates;
import be.bendem.chess.Direction;
import be.bendem.chess.Move;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author bendem
 */
public abstract class AbstractPiece {

    protected final Color color;
    protected final Coordinates coordinates;

    protected AbstractPiece(Color color, Coordinates coordinates) {
        this.color = color;
        this.coordinates = coordinates;
    }

    public abstract boolean canMove(Board board, Move move);
    public abstract Set<Direction> getAllDirections();

    public Set<Direction> getDirections() {
        Set<Direction> directions = new HashSet<>();

        for(Direction direction : getAllDirections()) {
            if(!Coordinates.overflow(coordinates, direction)) {
                directions.add(direction);
            }
        }

        return Collections.unmodifiableSet(directions);
    }

    public Color getColor() {
        return color;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

}
