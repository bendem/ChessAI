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

public class Chess {

    public static Timer TIMER = new Timer().start(Part.Init);

    public static void main(String[] args) throws InterruptedException, IOException {
        Board board = new Board();
        MoveGenerator moveGenerator = new MoveGenerator(board);
        Color currentColor = Color.White;
        UI ui = new UI(board);

        TIMER.stop();

        for(int i = 0; i < 300; i++) {
            TIMER.start(Part.Game);
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

            TIMER.start(Part.UpdateUI);
            ui.refresh();
            TIMER.stop();

            Logger.debug("%s (%s) from %s to %s",
                board.get(move.getFrom()).getType(),
                board.get(move.getFrom()).getColor(),
                move.getFrom(),
                move.getTo());

            TIMER.stop();
            //Thread.sleep(300);

            currentColor = currentColor == Color.Black ? Color.White : Color.Black;
        }

        System.out.println(TIMER.report().generate());
    }

}
