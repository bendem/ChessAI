package be.bendem.chess.pieces;

import be.bendem.chess.Board;
import be.bendem.chess.Direction;
import be.bendem.chess.Move;
import be.bendem.chess.Position;
import be.bendem.chess.iterators.PositionIterator;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * TODO Refactor this so that the piece isn't aware of the board state.
 *      The Piece class would only provide generic information about where
 *      it can move from a provided place or if it could move from one point
 *      to another on a provided board.
 */
public abstract class PieceHandler {

    private static final Map<Type, PieceHandler> HANDLERS;
    static {
        Map<Type, PieceHandler> map = new EnumMap<>(Type.class);

        map.put(Type.Bishop, new Bishop());
        map.put(Type.King, new King());
        map.put(Type.Knight, new Knight());
        map.put(Type.Pawn, new Pawn());
        map.put(Type.Queen, new Queen());
        map.put(Type.Rook, new Rook());

        HANDLERS = Collections.unmodifiableMap(map);
    }

    public static PieceHandler forType(Type type) {
        return HANDLERS.get(type);
    }

    protected final boolean isMoveCountRestricted;
    protected final Type type;

    protected PieceHandler(boolean isMoveCountRestricted) {
        this.isMoveCountRestricted = isMoveCountRestricted;
        this.type = Type.valueOf(getClass().getSimpleName());
    }

    /**
     * Returns all the directions the piece could theoretically go in.
     */
    public abstract Set<Direction> getAllDirections();

    /**
     * Checks whether a piece could go from one position on the provided board to the other.
     *
     * This method should not check for invalid directions since the move will be generated using
     * the ones it provided.
     * For the same reason, this method should not check for board overflow.
     *
     * @param board the board state to check the move on
     * @param move the move to check
     * @return whether the move is ok to do or not
     */
    public boolean canMove(Board board, Move move) {
        Validate.isTrue(board.get(move.getFrom()).type == type, "Can't validate the move of another piece");

        if(move.getCount() > 1 && isMoveCountRestricted) {
            return false;
        }

        Iterator<Position> it = new PositionIterator(move.getFrom(), move.getDirection());
        int count = 0;

        while(it.hasNext() && count < move.getCount()) {
            Position current = it.next();
            ++count;

            if(!board.isEmpty(current)) {
                // Another piece is in the way
                if(board.get(current).color == board.get(move.getFrom()).color) {
                    return false;
                } else {
                    return count == move.getCount();
                }
            }
        }

        return true;
    }

    /**
     * Returns all the directions the piece could go in without moving out of the board.
     *
     * @param position the position the piece would start on
     * @return the available directions that piece can go in from this position
     */
    public Set<Direction> getDirections(Position position) {
        Set<Direction> directions = getAllDirections();

        Iterator<Direction> iterator = directions.iterator();
        while(iterator.hasNext()) {
            if(Position.overflow(position, iterator.next())) {
                iterator.remove();
            }
        }

        return Collections.unmodifiableSet(directions);
    }

    public boolean isMoveCountRestricted() {
        return isMoveCountRestricted;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
