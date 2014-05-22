package be.bendem.chess.iterators;

import be.bendem.chess.Coordinates;
import be.bendem.chess.Direction;

import java.util.Iterator;

/**
 * @author bendem
 */
public class CoordinatesIterator implements Iterator<Coordinates> {

    private Coordinates current;
    private final Direction direction;

    public CoordinatesIterator(Coordinates coordinates, Direction direction) {
        this.direction = direction;
        this.current = coordinates.clone();
    }

    @Override
    public boolean hasNext() {
        switch(direction) {
            case Up:
                return current.getY() > 0;
            case Down:
                return current.getY() < 7;
            case Left:
                return current.getX() > 0;
            case Right:
                return current.getX() < 7;
            case LeftUp:
                return current.getX() > 0 && current.getY() > 0;
            case LeftDown:
                return current.getX() > 0 && current.getY() < 7;
            case RightUp:
                return current.getX() < 7 && current.getY() > 0;
            case RightDown:
                return current.getX() < 7 && current.getY() < 7;
            default:
                throw new AssertionError("Unknown direction");
        }
    }

    @Override
    public Coordinates next() {
        current = new Coordinates(current.getX() + direction.getX(), current.getY() + direction.getY());
        return current;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
