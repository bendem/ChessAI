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
        getNext();
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public AbstractPiece next() {
        AbstractPiece piece = board.get(current);
        getNext();
        return piece;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    private void getNext() {
        while(!filter.keep(board.get(current))) {
            // Move
            if(current.getX() == 7) {
                current.setX(0);
                // End of the board
                if(current.getY() == 7) {
                    hasNext = false;
                    return;
                }
                current.setY(current.getY()+1);
            } else {
                current.setX(current.getX()+1);
            }
        }
        hasNext = true;

    }

}
