package be.bendem.chess.pieces;

import be.bendem.chess.Board;
import be.bendem.chess.Color;
import be.bendem.chess.Coordinates;
import be.bendem.chess.Direction;
import be.bendem.chess.Move;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/**
 * @author bendem
 */
public abstract class AbstractPiece {

    protected final Color color;
    protected final Coordinates coordinates;
    protected final boolean isMoveCountRestricted;
    protected final Type type;
    protected boolean hasMoved = false;

    protected AbstractPiece(Color color, Coordinates coordinates, boolean isMoveCountRestricted) {
        this.color = color;
        this.coordinates = coordinates;
        this.isMoveCountRestricted = isMoveCountRestricted;
        this.type = Type.valueOf(getClass().getSimpleName());
    }

    public abstract Set<Direction> getAllDirections();

    public boolean canMove(Board board, Move move) {
        if(!getDirections().contains(move.getDirection())) {
            return false;
        }

        Iterator<Coordinates> it = board.iterator(move.getFrom(), move.getDirection());
        int count = 0;

        while(it.hasNext() && count < move.getCount()) {
            Coordinates current = it.next();
            ++count;

            if(!board.isEmpty(current)) {
                AbstractPiece piece = board.get(current);
                if(piece.getColor() == move.getPiece().getColor()) {
                    return false;
                } else {
                    break;
                }
            }
        }

        return count == move.getCount();
    }

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
        hasMoved = true;
    }

    public Color getColor() {
        return color;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public boolean isMoveCountRestricted() {
        return isMoveCountRestricted;
    }

    public Type getType() {
        return type;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    @Override
    public String toString() {
        return type.name() + "{" + "color=" + color + ", coordinates=" + coordinates + ", hasMoved=" + hasMoved + '}';
    }

}
