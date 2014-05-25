package be.bendem.chess;

import be.bendem.chess.filter.Filter;
import be.bendem.chess.iterators.BoardIterator;
import be.bendem.chess.iterators.CoordinatesIterator;
import be.bendem.chess.pieces.AbstractPiece;
import be.bendem.chess.pieces.Bishop;
import be.bendem.chess.pieces.King;
import be.bendem.chess.pieces.Knight;
import be.bendem.chess.pieces.Pawn;
import be.bendem.chess.pieces.Queen;
import be.bendem.chess.pieces.Rook;
import org.apache.commons.lang3.Validate;

import java.util.Iterator;

/**
 * @author bendem
 */
public class Board {

    private AbstractPiece[][] board;

    public Board() {
        board = new AbstractPiece[8][8];

        initRow(0, Color.Black);
        initRow(1, Color.Black);
        initRow(6, Color.White);
        initRow(7, Color.White);
    }

    private void initRow(int row, Color color) {
        if(row == 1 || row == 6) {
            for(int i = 0; i < 8; i++) {
                board[row][i] = new Pawn(color, row == 1 ? Direction.Down : Direction.Up, new Coordinates(i, row));
            }
            return;
        }

        board[row][0] = new Rook(color, new Coordinates(0, row));
        board[row][1] = new Knight(color, new Coordinates(1, row));
        board[row][2] = new Bishop(color, new Coordinates(2, row));
        board[row][3] = new Queen(color, new Coordinates(3, row));
        board[row][4] = new King(color, new Coordinates(4, row));
        board[row][5] = new Bishop(color, new Coordinates(5, row));
        board[row][6] = new Knight(color, new Coordinates(6, row));
        board[row][7] = new Rook(color, new Coordinates(7, row));
    }

    public AbstractPiece get(Coordinates coordinates) {
        return board[coordinates.getY()][coordinates.getX()];
    }

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

        if(kingMove.getDirection() == Direction.KingCastleLeft && !isEmpty(new Coordinates(kingMove.getFrom(), Direction.Left))) {
            return false;
        }

        // TODO Check if king.getTo or rook.getTo is menaced
        return true;
    }

    public void move(Move move) {
        Coordinates from = move.getFrom();
        Coordinates to = move.getTo();

        board[to.getY()][to.getX()] = board[from.getY()][from.getX()];
        board[from.getY()][from.getX()] = null;

        move.getPiece().move(to);
    }

    public Move createMove(Coordinates coordinates, Direction direction, int count) {
        return new Move(get(coordinates), coordinates, direction, count);
    }

    public Castle createCastle(King king, Direction direction) {
        Validate.isTrue(Direction.getKingCastles().contains(direction));

        Coordinates rookCoord = new Coordinates(direction.isLeft() ? 0 : 7, king.getCoordinates().getY());

        Move kingMove = createMove(king.getCoordinates(), direction, 1);
        Move rookMove = createMove(rookCoord, direction == Direction.KingCastleLeft ? Direction.RookCastleRight : Direction.RookCastleLeft, 1);

        return new Castle(kingMove, rookMove);
    }

    public boolean isEmpty(Coordinates coordinates) {
        return get(coordinates) == null;
    }

    public Iterator<AbstractPiece> iterator() {
        return iterator(null);
    }

    public Iterator<AbstractPiece> iterator(Filter<AbstractPiece> filter) {
        return new BoardIterator(this, filter);
    }

    public Iterator<Coordinates> iterator(Coordinates coordinates, Direction direction) {
        return new CoordinatesIterator(coordinates, direction);
    }

    public static boolean isWhite(Coordinates coordinates) {
        return (coordinates.getX()+coordinates.getY()) % 2 == 0;
    }

    public static boolean isBlack(Coordinates coordinates) {
        return !isWhite(coordinates);
    }

}
