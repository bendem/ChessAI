package be.bendem.chess.ai;

import be.bendem.chess.Board;
import be.bendem.chess.Chess;
import be.bendem.chess.Move;
import be.bendem.chess.Piece;
import be.bendem.chess.utils.timer.Part;

public class MoveRanker {

    private final Board board;

    public MoveRanker(Board board) {
        this.board = board;
    }

    public int rank(Move move) {
        Chess.TIMER.start(Part.RankMove);

        try {
            Piece piece = board.get(move.getTo());
            if(piece == null) {
                return 0;
            }

            switch(piece.type) {
                case King:
                    return Integer.MAX_VALUE;
                case Queen:
                    return 50;
                case Bishop:
                case Rook:
                case Knight:
                    return 20;
                case Pawn:
                    return 5;
            }

            throw new AssertionError();
        } finally {
            Chess.TIMER.stop();
        }
    }

    public int compare(Move move1, Move move2) {
        return Integer.compare(rank(move2), rank(move1));
    }

}
