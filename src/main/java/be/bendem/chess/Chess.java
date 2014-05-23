package be.bendem.chess;

import be.bendem.chess.ai.MoveGenerator;

/**
 * @author bendem
 */
public class Chess {

    public static void main(String[] args) {
        Board board = new Board();
        MoveGenerator moveGenerator = new MoveGenerator(board);
        for(Move move : moveGenerator.generate(Color.Black)) {
            System.out.println(move);
        }
    }

}
