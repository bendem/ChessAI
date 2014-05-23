package be.bendem.chess.pieces;

import be.bendem.chess.Board;
import be.bendem.chess.Color;
import be.bendem.chess.Coordinates;
import be.bendem.chess.Direction;
import be.bendem.chess.Move;

import java.util.Set;

/**
 * @author bendem
 */
public class Knight extends AbstractPiece {

    public Knight(Color color, Coordinates coordinates) {
        super(color, coordinates, true);
    }

    @Override
    public boolean canMove(Board board, Move move) {
        if(move.getCount() > 1) {
            return false;
        }

        if(!Direction.getKnights().contains(move.getDirection())) {
            return false;
        }

        if(Coordinates.overflow(coordinates, move.getDirection())) {
            return false;
        }

        return board.isEmpty(move.getTo()) || board.get(move.getTo()).getColor() != color;
    }

    @Override
    public Set<Direction> getAllDirections() {
        return Direction.getKnights();
    }

}
