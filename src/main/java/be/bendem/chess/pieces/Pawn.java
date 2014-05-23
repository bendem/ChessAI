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
    private boolean hasMoved = false;

    public Pawn(Color color, Direction direction, Coordinates coordinates) {
        super(color, coordinates);
        this.direction = direction;
    }

    @Override
    public void move(Coordinates to) {
        super.move(to);
        hasMoved = true;
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
            || Direction.getDiagonals().contains(moveDirection) && board.get(move.getTo()).getColor() != color;
    }

    @Override
    public Set<Direction> getAllDirections() {
        return EnumSet.of(direction);
    }

}
