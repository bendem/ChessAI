package be.bendem.chess.ui;

import be.bendem.chess.Board;
import be.bendem.chess.GameBoard;
import be.bendem.chess.Piece;
import be.bendem.chess.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bendem
 */
public class UI extends JFrame {

    private final Board board;
    private final List<JLabel> list;

    public UI(Board board) {
        super("Chess UI");

        this.board = board;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(8, 8));

        list = new ArrayList<>();
        Dimension labelDimension = new Dimension(75, 75);

        for(int i = 0; i < 64; i++) {
            JLabel x = new JLabel();
            x.setPreferredSize(labelDimension);
            x.setHorizontalAlignment(JLabel.CENTER);
            x.setVerticalAlignment(JLabel.CENTER);
            x.setOpaque(true);
            if(GameBoard.isWhite(new Position(i % Board.WIDTH, i / Board.HEIGHT))) {
                x.setBackground(Color.LIGHT_GRAY);
            } else {
                x.setBackground(Color.DARK_GRAY);
            }
            list.add(x);
            getContentPane().add(x);
        }

        refresh();

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void refresh() {
        Position pos = new Position();

        for(int i = 0; i < 64; i++) {
            JLabel x = list.get(i);
            pos.setX(i % Board.WIDTH);
            pos.setY(i / Board.HEIGHT);
            Piece piece = board.get(pos);
            if(piece == null) {
                x.setText("");
            } else {
                x.setForeground(piece.color == be.bendem.chess.Color.Black ? Color.BLACK : Color.WHITE);
                x.setText(piece.type.name().toUpperCase().substring(0, 2));
            }
        }
    }

    public static void main(String[] args) {
        new UI(new GameBoard());
    }

}
