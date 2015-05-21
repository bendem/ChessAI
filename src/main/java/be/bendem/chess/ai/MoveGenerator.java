package be.bendem.chess.ai;

import be.bendem.chess.Board;
import be.bendem.chess.Color;
import be.bendem.chess.Coordinates;
import be.bendem.chess.Direction;
import be.bendem.chess.Move;
import be.bendem.chess.filter.ColorPredicate;
import be.bendem.chess.pieces.AbstractPiece;
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

        Iterator<AbstractPiece> pieceIterator = board.iterator(new ColorPredicate(color));

        Timer.startNanoTimer();

        while(pieceIterator.hasNext()) {
            AbstractPiece piece = pieceIterator.next();
            for(Direction direction : piece.getDirections()) {
                if(piece.isMoveCountRestricted()) {
                    // TODO Handle King castling
                    Move move = board.createMove(piece.getCoordinates(), direction, 1);
                    if(piece.canMove(board, move)) {
                        moves.add(move);
                    }

                } else {
                    Iterator<Coordinates> coordinatesIterator = board.iterator(piece.getCoordinates(), direction);
                    int count = 0;
                    while(coordinatesIterator.hasNext()) {
                        coordinatesIterator.next();
                        ++count;
                        Move move = board.createMove(piece.getCoordinates(), direction, count);
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
