package be.bendem.chess.pieces;

import be.bendem.chess.Board;
import be.bendem.chess.Color;
import be.bendem.chess.Coordinates;
import be.bendem.chess.Direction;
import be.bendem.chess.Move;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author bendem
 */
public class Rook extends AbstractPiece {

    public Rook(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    public boolean canMove(Board board, Move move) {
        if(!getDirections().contains(move.getDirection())) {
            return false;
        }

        Iterator<Coordinates> it = board.iterator(move.getFrom(), move.getDirection());
        int count = 0;

        while(it.hasNext() && count < move.getCount()) {
            Coordinates current = it.next();
            ++count;

            if(!board.isEmpty(current)) {
                AbstractPiece piece = board.get(current);
                if(piece.getColor() == move.getPiece().getColor()) {
                    return false;
                } else {
                    break;
                }
            }
        }

        return count == move.getCount();
    }

    @Override
    public Collection<Direction> getAllDirections() {
        return Arrays.asList(Direction.getStraights());
    }

}
