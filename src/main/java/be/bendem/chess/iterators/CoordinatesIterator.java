package be.bendem.chess.iterators;

import be.bendem.chess.Coordinates;
import be.bendem.chess.Direction;

import java.util.Iterator;

/**
 * @author bendem
 */
public class CoordinatesIterator implements Iterator<Coordinates> {

    private final Coordinates current;
    private final Direction direction;

    public CoordinatesIterator(Coordinates coordinates, Direction direction) {
        this.direction = direction;
        this.current = new Coordinates(coordinates);
    }

    @Override
    public boolean hasNext() {
        return !Coordinates.overflow(current, direction);
    }

    @Override
    public Coordinates next() {
        current.add(direction);
        return new Coordinates(current);
    }

}
