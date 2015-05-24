package be.bendem.chess.ai;

import be.bendem.chess.Board;
import be.bendem.chess.Chess;
import be.bendem.chess.Color;
import be.bendem.chess.Move;
import be.bendem.chess.utils.timer.Part;

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
        Chess.TIMER.start(Part.GenerateMove);
        return Stream.of();
/*
        try {
            return board.stream()
                .filter(ColorPredicate.of(color))
                .map(this::generateMovesForPiece)
                .flatMap(pieces -> pieces)
                .filter(move -> PieceHandler.forType(board.get(move.getFrom()).type).canMove(board, move))
                .sorted(moveRanker::compare);
        } finally {
            Chess.TIMER.stop();
        }

    }

    private Stream<Move> generateMovesForPiece(Piece piece) {
        if(piece.isMoveCountRestricted()) {
            // TODO Handle King castling
            return PieceHandler.forType(piece.type).getDirections().stream()
                .map(direction -> new Move(piece.getPosition(), direction));
        }

        return piece.getDirections().stream()
            .flatMap(direction -> generateMovesForDirection(piece, direction));
    }

    private Stream<Move> generateMovesForDirection(PieceHandler piece, Direction direction) {
        List<Move> moves = new ArrayList<>();
        Iterator<Position> coordinatesIterator = board.iterator(piece.getPosition(), direction);
        int count = 0;

        while(coordinatesIterator.hasNext()) {
            coordinatesIterator.next();
            ++count;
            moves.add(board.createMove(piece.getPosition(), direction, count));
        }

        return moves.stream();
*/
    }

}
