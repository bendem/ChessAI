package be.bendem.chess.filter;

import be.bendem.chess.pieces.AbstractPiece;

/**
 * @author bendem
 */
public class PieceFilter implements Filter<AbstractPiece> {

    @Override
    public boolean keep(AbstractPiece element) {
        return element != null;
    }

}
