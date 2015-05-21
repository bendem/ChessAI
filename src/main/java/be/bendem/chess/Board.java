package be.bendem.chess;

import be.bendem.chess.iterators.BoardIterator;
import be.bendem.chess.iterators.CoordinatesIterator;
import be.bendem.chess.pieces.*;
import org.apache.commons.lang3.Validate;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author bendem
 */
public class Board implements Iterable<Piece> {

    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private final Piece[][] board;

    public Board() {
        board = new Piece[WIDTH][HEIGHT];

        initRow(0, Color.Black);
        initRow(1, Color.Black);
        initRow(6, Color.White);
        initRow(7, Color.White);
    }

    private void initRow(int row, Color color) {
        if(row == 1 || row == 6) {
            Direction direction = row == 1 ? Direction.Down : Direction.Up;
            for(int i = 0; i < WIDTH; i++) {
                board[row][i] = new Pawn(color, direction, new Position(i, row));
            }
            return;
        }

        for(int i = 0; i < WIDTH; i++) {
            switch(i) {
                case 0:
                case 7:
                    board[row][i] = new Rook(color, new Position(i, row));
                    break;
                case 1:
                case 6:
                    board[row][i] = new Knight(color, new Position(i, row));
                    break;
                case 2:
                case 5:
                    board[row][i] = new Bishop(color, new Position(i, row));
                    break;
                case 3:
                    board[row][i] = new Queen(color, new Position(i, row));
                    break;
                case 4:
                    board[row][i] = new King(color, new Position(i, row));
                    break;
            }
        }
    }

    public Piece get(Position position) {
        return board[position.getY()][position.getX()];
    }

    /**
     * Check if the castle moves are valid on the board
     *
     * @param castle The move to check
     * @return Returns true if the moves can be executed, false otherwise
     */
    public boolean canCastle(Castle castle) {
        if(!castle.isValid()) {
            return false;
        }

        Move kingMove = castle.getKingMove();
        Move rookMove = castle.getRookMove();
        if(kingMove.getPiece().hasMoved() || rookMove.getPiece().hasMoved()) {
            return false;
        }

        if(!isEmpty(kingMove.getTo()) || !isEmpty(rookMove.getTo())) {
            return false;
        }

        if(kingMove.getDirection() == Direction.KingCastleLeft && !isEmpty(new Position(kingMove.getFrom(), Direction.Left))) {
            return false;
        }

        // TODO Check if king.getTo or rook.getTo is menaced
        return true;
    }

    public void move(Move move) {
        Position from = move.getFrom();
        Position to = move.getTo();

        board[to.getY()][to.getX()] = board[from.getY()][from.getX()];
        board[from.getY()][from.getX()] = null;

        move.getPiece().move(to);
    }

    public Move createMove(Piece piece, Direction direction) {
        return createMove(piece.getPosition(), direction, 1);
    }

    public Move createMove(Piece piece, Direction direction, int count) {
        return createMove(piece.getPosition(), direction, count);
    }

    public Move createMove(Position position, Direction direction) {
        return createMove(position, direction, 1);
    }

    public Move createMove(Position position, Direction direction, int count) {
        return new Move(get(position), position, direction, count);
    }

    public Castle createCastle(King king, Direction direction) {
        Validate.isTrue(Direction.getKingCastles().contains(direction));

        Position rookCoord = new Position(direction.isLeft() ? 0 : 7, king.getPosition().getY());

        Move kingMove = createMove(king.getPosition(), direction, 1);
        Move rookMove = createMove(rookCoord, direction == Direction.KingCastleLeft ? Direction.RookCastleRight : Direction.RookCastleLeft, 1);

        return new Castle(kingMove, rookMove);
    }

    public boolean isEmpty(Position position) {
        return get(position) == null;
    }

    @Override
    public Iterator<Piece> iterator() {
        return iterator(null);
    }

    public Iterator<Piece> iterator(Predicate<Piece> filter) {
        return new BoardIterator(this, filter);
    }

    public Iterator<Position> iterator(Position position, Direction direction) {
        return new CoordinatesIterator(position, direction);
    }

    public Stream<Piece> stream() {
        return Arrays.stream(this.board).flatMap(Arrays::stream);
    }

    public static boolean isWhite(Position position) {
        return (position.getX() + position.getY()) % 2 == 0;
    }

    public static boolean isBlack(Position position) {
        return !isWhite(position);
    }

}
