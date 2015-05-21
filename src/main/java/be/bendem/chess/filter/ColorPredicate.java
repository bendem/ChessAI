package be.bendem.chess.filter;

import be.bendem.chess.Color;
import be.bendem.chess.pieces.AbstractPiece;

/**
 * @author bendem
 */
public class ColorPredicate extends PiecePredicate {

    private final Color color;

    public ColorPredicate(Color color) {
        this.color = color;
    }

    @Override
    public boolean test(AbstractPiece element) {
        return super.test(element) && element.getColor() == color;
    }

}
