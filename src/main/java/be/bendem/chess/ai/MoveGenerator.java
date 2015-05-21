package be.bendem.chess.ai;

import be.bendem.chess.Board;
import be.bendem.chess.Color;
import be.bendem.chess.Position;
import be.bendem.chess.Direction;
import be.bendem.chess.Move;
import be.bendem.chess.predicates.ColorPredicate;
import be.bendem.chess.pieces.Piece;
import be.bendem.chess.utils.Timer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author bendem
 */
public class MoveGenerator {

    private final Board board;

    public MoveGenerator(Board board) {
        this.board = board;
    }

    public List<Move> generate(Color color) {
        List<Move> moves = new ArrayList<>();

        Iterator<Piece> pieceIterator = board.iterator(new ColorPredicate(color));

        Timer.startNanoTimer();

        while(pieceIterator.hasNext()) {
            Piece piece = pieceIterator.next();
            for(Direction direction : piece.getDirections()) {
                if(piece.isMoveCountRestricted()) {
                    // TODO Handle King castling
                    Move move = board.createMove(piece.getPosition(), direction, 1);
                    if(piece.canMove(board, move)) {
                        moves.add(move);
                    }

                } else {
                    Iterator<Position> coordinatesIterator = board.iterator(piece.getPosition(), direction);
                    int count = 0;
                    while(coordinatesIterator.hasNext()) {
                        coordinatesIterator.next();
                        ++count;
                        Move move = board.createMove(piece.getPosition(), direction, count);
                        if(piece.canMove(board, move)) {
                            moves.add(move);
                        }
                    }
                }
            }
        }

        System.out.println(String.valueOf(moves.size()) + " moves generated in " + Timer.formatNanoSecs(Timer.stopNanoTimer()));

        return moves;
    }

}
