package be.bendem.chess.filter;

import be.bendem.chess.Color;
import be.bendem.chess.pieces.AbstractPiece;

/**
 * @author bendem
 */
public class ColorFilter extends PieceFilter {

    private final Color color;

    public ColorFilter(Color color) {
        this.color = color;
    }

    @Override
    public boolean keep(AbstractPiece element) {
        return super.keep(element) && element.getColor() == color;
    }

}
