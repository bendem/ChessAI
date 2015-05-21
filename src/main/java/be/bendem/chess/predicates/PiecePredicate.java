package be.bendem.chess.predicates;

import be.bendem.chess.pieces.Piece;

import java.util.function.Predicate;

/**
 * @author bendem
 */
public class PiecePredicate implements Predicate<Piece> {

    @Override
    public boolean test(Piece element) {
        return element != null;
    }

}
