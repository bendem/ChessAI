package be.bendem.chess;

import be.bendem.chess.ai.MoveGenerator;
import be.bendem.chess.pieces.Piece;
import be.bendem.chess.pieces.Type;
import be.bendem.chess.ui.UI;
import be.bendem.chess.utils.Logger;
import be.bendem.chess.utils.timer.Part;
import be.bendem.chess.utils.timer.Timer;

import java.io.IOException;
import java.util.Optional;

/**
 * @author bendem
 */
public class Chess {

    public static void main(String[] args) throws InterruptedException, IOException {
        Timer.start(Part.Init);

        Board board = new Board();
        MoveGenerator moveGenerator = new MoveGenerator(board);
        Color currentColor = Color.White;
        UI ui = new UI(board);

        Timer.stop();

        for(int i = 0; i < 300; i++) {
            Timer.start(Part.Game);
            Logger.debug("Turn %d", i);

            Optional<Move> optMove = moveGenerator.generate(currentColor).findFirst();
            if(!optMove.isPresent()) {
                Logger.info("Can't move anymore");
                break;
            }
            Move move = optMove.get();

            Piece piece = board.get(move.getTo());

            if(piece != null && piece.getType() == Type.King) {
                Logger.info("%s WIN!", currentColor.name());
                break;
            }
            board.move(move);

            Timer.start(Part.UpdateUI);
            ui.refresh();
            Timer.stop();

            Logger.debug("%s (%s) from %s to %s",
                move.getPiece().getType(),
                move.getPiece().getColor(),
                move.getFrom(),
                move.getTo());

            Timer.stop();
            Thread.sleep(100);

            currentColor = currentColor == Color.Black ? Color.White : Color.Black;
        }

        System.out.println(Timer.report().generate());
    }

}
