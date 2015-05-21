package be.bendem.chess.predicates;

import be.bendem.chess.Color;
import be.bendem.chess.pieces.Piece;

/**
 * @author bendem
 */
public class ColorPredicate extends PiecePredicate {

    private final Color color;

    public ColorPredicate(Color color) {
        this.color = color;
    }

    @Override
    public boolean test(Piece element) {
        return super.test(element) && element.getColor() == color;
    }

}
