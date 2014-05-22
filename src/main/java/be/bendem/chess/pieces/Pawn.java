package be.bendem.chess.pieces;

import be.bendem.chess.Board;
import be.bendem.chess.Coordinates;
import be.bendem.chess.Direction;
import be.bendem.chess.Move;

/**
 * @author bendem
 */
public class Pawn extends AbstractPiece {

    private Direction direction;

    public Pawn(Color color, Direction direction, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    public boolean canMove(Board board, Move move) {
        return false;
    }

    public Direction getDirection() {
        return direction;
    }

}
