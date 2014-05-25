package be.bendem.chess;

/**
 * @author bendem
 */
public class Castle extends Move {

    private final Move rookMove;

    public Castle(Move kingMove, Move rookMove) {
        super(kingMove.getPiece(), kingMove.getFrom(), kingMove.getDirection(), kingMove.getCount());
        this.rookMove = rookMove;
    }



}
