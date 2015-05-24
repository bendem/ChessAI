package be.bendem.chess.pieces;

import be.bendem.chess.Board;
import be.bendem.chess.Direction;
import be.bendem.chess.Move;
import be.bendem.chess.Piece;
import be.bendem.chess.Position;

import java.util.Collections;
import java.util.Set;

/**
 * @author bendem
 */
public class Pawn extends PieceHandler {

    protected Pawn() {
        super(true);
    }

    @Override
    public boolean canMove(Board board, Move move) {
        Direction moveDirection = move.getDirection();

        // TODO Re add that
        //if(moveDirection.getY() != direction.getY()) {
            //moving the wrong way
            //return false;
        //}

        Piece piece = board.get(move.getFrom());
        boolean hasMoved = piece.hasMoved();

        if(!hasMoved && move.getCount() > 2 || hasMoved && move.getCount() > 1) {
            // If moving more than 1 or 2 (depending on if they moved before)
            return false;
        }

        if(Position.overflow(move.getFrom(), moveDirection)) {
            return false;
        }

        return Direction.getStraights().contains(moveDirection)
            && board.isEmpty(move.getTo())
            || Direction.getDiagonals().contains(moveDirection)
            && !board.isEmpty(move.getTo())
            && board.get(move.getTo()).color != board.get(move.getTo()).color;
    }

    @Override
    public Set<Direction> getAllDirections() {
        //EnumSet<Direction> set = EnumSet.of(direction);
        //if(direction == Direction.Up) {
        //    set.add(Direction.LeftUp);
        //    set.add(Direction.RightUp);
        //} else {
        //    set.add(Direction.LeftDown);
        //    set.add(Direction.RightDown);
        //}
        //return set;
        return Collections.emptySet();
    }

}
