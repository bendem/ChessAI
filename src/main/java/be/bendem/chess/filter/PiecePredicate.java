package be.bendem.chess.filter;

import be.bendem.chess.pieces.AbstractPiece;

import java.util.function.Predicate;

/**
 * @author bendem
 */
public class PiecePredicate implements Predicate<AbstractPiece> {

    @Override
    public boolean test(AbstractPiece element) {
        return element != null;
    }

}
