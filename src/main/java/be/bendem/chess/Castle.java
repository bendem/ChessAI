package be.bendem.chess;

import be.bendem.chess.pieces.Type;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author bendem
 */
public class Castle {

    private final Move kingMove;
    private final Move rookMove;

    public Castle(Move kingMove, Move rookMove) {
        this.kingMove = kingMove;
        this.rookMove = rookMove;
    }

    /**
     * Checks if the moves are valid independently of the board
     *
     * @return true if the moves are valid, false otherwise
     */
    public boolean isValid(Board board) {
        return (board.get(kingMove.getFrom()).getType() == Type.King)
            && (board.get(rookMove.getFrom()).getType() == Type.Rook)
            && (Direction.getKingCastles().contains(kingMove.getDirection()))
            && (Direction.getRookCastles().contains(rookMove.getDirection()))
            && (kingMove.getDirection().isLeft() != rookMove.getDirection().isLeft());
    }

    public Move getKingMove() {
        return kingMove;
    }

    public Move getRookMove() {
        return rookMove;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
