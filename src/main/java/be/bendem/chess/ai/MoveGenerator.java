package be.bendem.chess.ai;

import be.bendem.chess.Board;
import be.bendem.chess.Color;
import be.bendem.chess.Direction;
import be.bendem.chess.Move;
import be.bendem.chess.Position;
import be.bendem.chess.pieces.Piece;
import be.bendem.chess.predicates.ColorPredicate;
import be.bendem.chess.utils.timer.Part;
import be.bendem.chess.utils.timer.Timer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author bendem
 */
public class MoveGenerator {

    private final Board board;
    private final MoveRanker moveRanker;

    public MoveGenerator(Board board) {
        this.board = board;
        this.moveRanker = new MoveRanker(board);
    }

    public Stream<Move> generate(Color color) {
        Timer.start(Part.GenerateMove);

        try {
            return board.stream()
                .filter(ColorPredicate.of(color))
                .map(this::generateMovesForPiece)
                .flatMap(pieces -> pieces)
                .filter(move -> move.getPiece().canMove(board, move))
                .sorted(moveRanker::compare);
        } finally {
            Timer.stop();
        }
    }

    private Stream<Move> generateMovesForPiece(Piece piece) {

        if(piece.isMoveCountRestricted()) {
            // TODO Handle King castling
            return piece.getDirections().stream()
                .map(direction -> board.createMove(piece.getPosition(), direction, 1));
        }

        return piece.getDirections().stream()
            .flatMap(direction -> generateMovesForDirection(piece, direction));
    }

    private Stream<Move> generateMovesForDirection(Piece piece, Direction direction) {
        List<Move> moves = new ArrayList<>();
        Iterator<Position> coordinatesIterator = board.iterator(piece.getPosition(), direction);
        int count = 0;

        while(coordinatesIterator.hasNext()) {
            coordinatesIterator.next();
            ++count;
            moves.add(board.createMove(piece.getPosition(), direction, count));
        }

        return moves.stream();
    }

}
