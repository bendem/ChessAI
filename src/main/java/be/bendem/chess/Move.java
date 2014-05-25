package be.bendem.chess;

import be.bendem.chess.pieces.AbstractPiece;
import org.apache.commons.lang3.Validate;

/**
 * @author bendem
 */
public class Move {

    protected final AbstractPiece piece;
    protected final Coordinates   from;
    protected final Direction     direction;
    protected final int           count;
    protected final Coordinates   to;

    public Move(AbstractPiece piece, Coordinates from, Direction direction, int count) {
        Validate.isTrue(count > 0);

        this.piece = piece;
        this.from = from;
        this.direction = direction;
        this.count = count;
        to = from.clone();
        for(int i = 0; i < count; i++) {
            to.add(direction);
        }
    }

    public AbstractPiece getPiece() {
        return piece;
    }

    public Coordinates getFrom() {
        return from;
    }

    public Coordinates getTo() {
        return to;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + "piece=" + piece + ", from=" + from + ", direction=" + direction + ", count=" + count + ", to=" + to + '}';
    }

}
