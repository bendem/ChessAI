package be.bendem.chess.pieces;

import be.bendem.chess.Board;
import be.bendem.chess.Color;
import be.bendem.chess.Coordinates;
import be.bendem.chess.Direction;
import be.bendem.chess.Move;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author bendem
 */
public class Pawn extends AbstractPiece {

    private Direction direction;

    public Pawn(Color color, Direction direction, Coordinates coordinates) {
        super(color, coordinates);
        this.direction = direction;
    }

    @Override
    public boolean canMove(Board board, Move move) {
        return false;
    }

    @Override
    public Collection<Direction> getAllDirections() {
        return Arrays.asList(direction);
    }

}
