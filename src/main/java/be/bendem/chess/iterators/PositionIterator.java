package be.bendem.chess.iterators;

import be.bendem.chess.Position;
import be.bendem.chess.Direction;

import java.util.Iterator;

/**
 * @author bendem
 */
public class PositionIterator implements Iterator<Position> {

    private final Position current;
    private final Direction direction;

    public PositionIterator(Position position, Direction direction) {
        this.direction = direction;
        this.current = new Position(position);
    }

    @Override
    public boolean hasNext() {
        return !Position.overflow(current, direction);
    }

    @Override
    public Position next() {
        current.add(direction);
        return new Position(current);
    }

}
