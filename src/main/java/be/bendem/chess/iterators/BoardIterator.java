package be.bendem.chess.iterators;

import be.bendem.chess.Board;
import be.bendem.chess.Position;
import be.bendem.chess.pieces.Piece;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 * @author bendem
 */
public class BoardIterator implements Iterator<Piece> {

    private final Board board;
    private final Predicate<Piece> filter;
    private Position current;
    private boolean hasNext;

    public BoardIterator(Board board, Predicate<Piece> filter) {
        this.board = board;
        this.filter = filter;
        current = new Position();
        getNext(false);
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public Piece next() {
        Piece piece = board.get(current);
        getNext(true);
        return piece;
    }

    private void getNext(boolean ignoreCurrent) {
        if(ignoreCurrent) {
            if(!incrementCurrent()) {
                hasNext = false;
                return;
            }
        }

        while(filter != null && !filter.test(board.get(current))) {
            if(!incrementCurrent()) {
                hasNext = false;
                return;
            }
        }
        hasNext = true;
    }

    private boolean incrementCurrent() {
        if(current.getX() == 7) {
            // End of the board
            if(current.getY() == 7) {
                return false;
            }
            current.setX(0);
            current.setY(current.getY()+1);
        } else {
            current.setX(current.getX()+1);
        }
        return true;
    }

}
