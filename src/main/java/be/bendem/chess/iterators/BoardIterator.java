package be.bendem.chess.iterators;

import be.bendem.chess.Board;
import be.bendem.chess.Coordinates;
import be.bendem.chess.filter.Filter;
import be.bendem.chess.pieces.AbstractPiece;

import java.util.Iterator;

/**
 * @author bendem
 */
public class BoardIterator implements Iterator<AbstractPiece> {

    private final Board board;
    private final Filter<AbstractPiece> filter;
    private Coordinates current;
    private boolean hasNext;

    public BoardIterator(Board board, Filter<AbstractPiece> filter) {
        this.board = board;
        this.filter = filter;
        current = new Coordinates(0, 0);
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

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    private void getNext(boolean ignoreCurrent) {
        if(ignoreCurrent) {
            if(!incrementCurrent()) {
                hasNext = false;
                return;
            }
        }

        while(filter != null && !filter.keep(board.get(current))) {
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
