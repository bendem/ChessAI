package be.bendem.chess;

import be.bendem.chess.ai.MoveGenerator;
import be.bendem.chess.pieces.Type;
import be.bendem.chess.ui.UI;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * @author bendem
 */
public class Chess {

    public static void main(String[] args) throws InterruptedException, IOException {
        Board board = new Board();
        MoveGenerator moveGenerator = new MoveGenerator(board);
        Color currentColor = Color.White;
        UI ui = new UI(board);
        Random random = new Random();

        for(int i = 0; i < 300; i++) {
            System.out.println("Turn " + i);
            List<Move> generated = moveGenerator.generate(currentColor);
            Move move = generated.get(random.nextInt(generated.size()));
            System.out.println(move);
            if(!board.isEmpty(move.getTo()) && board.get(move.getTo()).getType() == Type.King) {
                System.out.println(currentColor.name() + " WIN!");
                break;
            }
            board.move(move);
            ui.refresh();
            //System.console().readLine();
            Thread.sleep(250);
            currentColor = currentColor == Color.Black ? Color.White : Color.Black;
        }
    }

}
