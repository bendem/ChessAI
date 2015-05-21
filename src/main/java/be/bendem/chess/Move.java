package be.bendem.chess;

import be.bendem.chess.pieces.Piece;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author bendem
 */
public class Move {

    private final Piece piece;
    private final Position from;
    private final Direction direction;
    private final int count;
    private final Position to;

    public Move(Piece piece, Position from, Direction direction, int count) {
        Validate.isTrue(count > 0);

        this.piece = piece;
        this.from = from;
        this.direction = direction;
        this.count = count;
        this.to = new Position(from);

        for(int i = 0; i < count; i++) {
            to.add(direction);
        }
    }

    public Piece getPiece() {
        return piece;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
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
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
