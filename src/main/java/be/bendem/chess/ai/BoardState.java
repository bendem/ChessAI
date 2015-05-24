package be.bendem.chess.ai;

import be.bendem.chess.Board;
import be.bendem.chess.Move;
import be.bendem.chess.Piece;
import be.bendem.chess.Position;
import be.bendem.chess.utils.array2d.Array2D;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BoardState implements Board {

    private final Piece[][] pieces;
    private final List<Move> moves;

    public BoardState(Piece[][] pieces) {
        this.pieces = pieces;
        this.moves = new ArrayList<>();
    }

    public BoardState(BoardState boardState) {
        // Since this can't be modified, no point in copying!
        this.pieces = boardState.pieces;
        this.moves = new ArrayList<>(boardState.moves);
    }

    @Override
    public Piece get(Position position) {
        return Array2D.get(pieces, position);
    }

    @Override
    public Position getPosition(Piece piece) {
        return Array2D.searchPosition(pieces, p -> p.color == piece.color && p.type == piece.type).get();
    }

    @Override
    public Stream<Piece> stream() {
        return Array2D.stream(pieces);
    }

    @Override
    public boolean isEmpty(Position position) {
        return get(position) == null;
    }

    @Override
    public BoardState snapshot() {
        return new BoardState(this);
    }

    @Override
    public BoardState move(Move move) {

        moves.add(move);
        return this;
    }

}
