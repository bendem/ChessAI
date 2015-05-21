package be.bendem.chess.pieces;

import be.bendem.chess.Board;
import be.bendem.chess.Color;
import be.bendem.chess.Position;
import be.bendem.chess.Direction;
import be.bendem.chess.Move;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/**
 * @author bendem
 */
public abstract class Piece {

    protected final Color color;
    protected final Position position;
    protected final boolean isMoveCountRestricted;
    protected final Type type;
    protected boolean hasMoved = false;

    protected Piece(Color color, Position position, boolean isMoveCountRestricted) {
        this.color = color;
        this.position = position;
        this.isMoveCountRestricted = isMoveCountRestricted;
        this.type = Type.valueOf(getClass().getSimpleName());
    }

    public abstract Set<Direction> getAllDirections();

    public boolean canMove(Board board, Move move) {
        if(!getDirections().contains(move.getDirection())) {
            return false;
        }

        Iterator<Position> it = board.iterator(move.getFrom(), move.getDirection());
        int count = 0;

        while(it.hasNext() && count < move.getCount()) {
            Position current = it.next();
            ++count;

            if(!board.isEmpty(current)) {
                Piece piece = board.get(current);
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
            if(Position.overflow(position, iterator.next())) {
                iterator.remove();
            }
        }

        return Collections.unmodifiableSet(directions);
    }

    public void move(Position to) {
        position.setX(to.getX());
        position.setY(to.getY());
        hasMoved = true;
    }

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
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
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
