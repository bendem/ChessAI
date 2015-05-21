package be.bendem.chess.ai;

import be.bendem.chess.Board;
import be.bendem.chess.Move;
import be.bendem.chess.pieces.Piece;

public class MoveRanker {

    private final Board board;

    public MoveRanker(Board board) {
        this.board = board;
    }

    public int rank(Move move) {
        Piece piece = board.get(move.getTo());
        if(piece == null) {
            return 0;
        }

        switch(piece.getType()) {
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
    }

    public int compare(Move move1, Move move2) {
        return Integer.compare(rank(move2), rank(move1));
    }

}
