package be.bendem.chess;

import be.bendem.chess.pieces.Type;
import org.apache.commons.lang3.Validate;

/**
 * @author bendem
 */
public class Castle {

    private final Move kingMove;
    private final Move rookMove;

    public Castle(Move kingMove, Move rookMove) {
        Validate.isTrue(kingMove.getPiece().getType() == Type.King);
        Validate.isTrue(rookMove.getPiece().getType() == Type.Rook);
        Validate.isTrue(Direction.getKingCastles().contains(kingMove.getDirection()));
        Validate.isTrue(Direction.getRookCastles().contains(rookMove.getDirection()));
        Validate.isTrue(kingMove.getDirection().isLeft() != rookMove.getDirection().isLeft());

        this.kingMove = kingMove;
        this.rookMove = rookMove;
    }

    public Move getKingMove() {
        return kingMove;
    }

    public Move getRookMove() {
        return rookMove;
    }

}
