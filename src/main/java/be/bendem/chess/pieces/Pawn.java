package be.bendem.chess.pieces;

import be.bendem.chess.Board;
import be.bendem.chess.Color;
import be.bendem.chess.Coordinates;
import be.bendem.chess.Direction;
import be.bendem.chess.Move;

import java.util.EnumSet;
import java.util.Set;

/**
 * @author bendem
 */
public class Pawn extends AbstractPiece {

    private Direction direction;

    public Pawn(Color color, Direction direction, Coordinates coordinates) {
        super(color, coordinates, true);
        this.direction = direction;
    }

    @Override
    public boolean canMove(Board board, Move move) {
        Direction moveDirection = move.getDirection();
        if(moveDirection.getY() != direction.getY()) {
            // moving the wrong way
            return false;
        }

        if(!hasMoved && move.getCount() > 2 || hasMoved && move.getCount() > 1) {
            // If moving more than 1 or 2 (depending on if they moved before)
            return false;
        }

        if(Coordinates.overflow(coordinates, moveDirection)) {
            return false;
        }

        return Direction.getStraights().contains(moveDirection) && board.isEmpty(move.getTo())
            || Direction.getDiagonals().contains(moveDirection) && !board.isEmpty(move.getTo()) && board.get(move.getTo()).getColor() != color;
    }

    @Override
    public Set<Direction> getAllDirections() {
        EnumSet<Direction> set = EnumSet.of(direction);
        if(direction == Direction.Up) {
            set.add(Direction.LeftUp);
            set.add(Direction.RightUp);
        } else {
            set.add(Direction.LeftDown);
            set.add(Direction.RightDown);
        }
        return set;
    }

    @Override
    public boolean isMoveCountRestricted() {
        return isMoveCountRestricted && hasMoved;
    }

}
