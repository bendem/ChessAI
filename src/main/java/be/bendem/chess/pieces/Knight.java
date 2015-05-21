package be.bendem.chess.pieces;

import be.bendem.chess.Board;
import be.bendem.chess.Color;
import be.bendem.chess.Position;
import be.bendem.chess.Direction;
import be.bendem.chess.Move;

import java.util.Set;

/**
 * @author bendem
 */
public class Knight extends Piece {

    public Knight(Color color, Position position) {
        super(color, position, true);
    }

    @Override
    public boolean canMove(Board board, Move move) {
        if(move.getCount() > 1) {
            return false;
        }

        if(!Direction.getKnights().contains(move.getDirection())) {
            return false;
        }

        if(Position.overflow(position, move.getDirection())) {
            return false;
        }

        return board.isEmpty(move.getTo()) || board.get(move.getTo()).getColor() != color;
    }

    @Override
    public Set<Direction> getAllDirections() {
        return Direction.getKnights();
    }

}
