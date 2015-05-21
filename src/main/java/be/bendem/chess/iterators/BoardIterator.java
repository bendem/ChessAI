package be.bendem.chess.iterators;

import be.bendem.chess.Board;
import be.bendem.chess.Coordinates;
import be.bendem.chess.pieces.AbstractPiece;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 * @author bendem
 */
public class BoardIterator implements Iterator<AbstractPiece> {

    private final Board board;
    private final Predicate<AbstractPiece> filter;
    private Coordinates current;
    private boolean hasNext;

    public BoardIterator(Board board, Predicate<AbstractPiece> filter) {
        this.board = board;
        this.filter = filter;
        current = new Coordinates();
        getNext(false);
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public AbstractPiece next() {
        AbstractPiece piece = board.get(current);
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
