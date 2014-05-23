package be.bendem.chess.ai;

import be.bendem.chess.Board;
import be.bendem.chess.Color;
import be.bendem.chess.Coordinates;
import be.bendem.chess.Direction;
import be.bendem.chess.Move;
import be.bendem.chess.filter.ColorFilter;
import be.bendem.chess.iterators.CoordinatesIterator;
import be.bendem.chess.pieces.AbstractPiece;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

        Iterator<AbstractPiece> pieceIterator = board.iterator(new ColorFilter(color));

        long time = System.nanoTime();

        while(pieceIterator.hasNext()) {
            AbstractPiece piece = pieceIterator.next();
            for(Direction direction : piece.getDirections()) {
                if(piece.isMoveCountRestricted()) {
                    // TODO Handle pawn :/
                    // TODO Handle King castling
                    Move move = board.createMove(piece.getCoordinates(), direction, 1);
                    if(piece.canMove(board, move)) {
                        moves.add(move);
                    }

                } else {
                    Iterator<Coordinates> coordinatesIterator = new CoordinatesIterator(piece.getCoordinates(), direction);
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

        System.out.println(String.valueOf(moves.size()) + " moves generated in " + ((System.nanoTime() - time)/1000) + "µs");

        return moves;
    }

}
