package be.bendem.chess.pieces;

import be.bendem.chess.Board;
import be.bendem.chess.Color;
import be.bendem.chess.Coordinates;
import be.bendem.chess.Direction;
import be.bendem.chess.Move;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
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
        Set<Direction> directions = getAllDirections();

        Iterator<Direction> iterator = directions.iterator();
        while(iterator.hasNext()) {
            if(Coordinates.overflow(coordinates, iterator.next())) {
                iterator.remove();
            }
        }

        return Collections.unmodifiableSet(directions);
    }

    public void move(Coordinates to) {
        coordinates.setX(to.getX());
        coordinates.setY(to.getY());
    }

    public Color getColor() {
        return color;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

}
