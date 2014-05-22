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
                board[row][i] = new Pawn(color, row == 1 ? Direction.Down : Direction.Up);
            }
            return;
        }

        board[row][0] = new Rook(color);
        board[row][1] = new Knight(color);
        board[row][2] = new Bishop(color);
        board[row][3] = new Queen(color);
        board[row][4] = new King(color);
        board[row][5] = new Bishop(color);
        board[row][6] = new Knight(color);
        board[row][7] = new Rook(color);
    }

    public void move(Move move) {
        Coordinates from = move.getFrom();
        Coordinates to = move.getTo();

        board[to.getY()][to.getX()] = board[from.getY()][from.getX()];
        board[from.getY()][from.getX()] = null;
    }

    public static boolean isWhite(int x, int y) {
        return (x+y) % 2 == 0;
    }

    public static boolean isBlack(int x, int y) {
        return !isWhite(x, y);
    }

}
