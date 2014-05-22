package be.bendem.chess;

import be.bendem.chess.pieces.AbstractPiece;
import be.bendem.chess.pieces.Bishop;
import be.bendem.chess.pieces.Color;
import be.bendem.chess.pieces.King;
import be.bendem.chess.pieces.Knight;
import be.bendem.chess.pieces.Pawn;
import be.bendem.chess.pieces.Queen;
import be.bendem.chess.pieces.Rook;

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

    public AbstractPiece get(int x, int y) {
        return board[y][x];
    }

    public void move(Move move) {
        Coordinates from = move.getFrom();
        Coordinates to = move.getTo();

        board[to.getY()][to.getX()] = board[from.getY()][from.getX()];
        board[from.getY()][from.getX()] = null;

        move.getPiece().getCoordinates().setX(to.getX());
        move.getPiece().getCoordinates().setY(to.getY());
    }

    public static boolean isWhite(int x, int y) {
        return (x+y) % 2 == 0;
    }

    public static boolean isBlack(int x, int y) {
        return !isWhite(x, y);
    }

}
