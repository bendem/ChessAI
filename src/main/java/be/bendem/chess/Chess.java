package be.bendem.chess;

import be.bendem.chess.ai.MoveGenerator;
import be.bendem.chess.pieces.Piece;
import be.bendem.chess.pieces.Type;
import be.bendem.chess.ui.UI;
import be.bendem.chess.utils.Logger;

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
            Logger.debug("Turn %d", i);

            List<Move> generated = moveGenerator.generate(currentColor);
            Move move = generated.get(random.nextInt(generated.size()));
            Piece piece = board.get(move.getTo());

            if(piece != null && piece.getType() == Type.King) {
                Logger.info("%s WIN!", currentColor.name());
                break;
            }
            board.move(move);
            ui.refresh();

            Logger.debug("%s (%s) from %s to %s",
                move.getPiece().getType(),
                move.getPiece().getColor(),
                move.getFrom(),
                move.getTo());
            Thread.sleep(100);

            currentColor = currentColor == Color.Black ? Color.White : Color.Black;
        }
    }

}
