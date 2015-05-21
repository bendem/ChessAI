package be.bendem.chess.ui;

import be.bendem.chess.Board;
import be.bendem.chess.Position;
import be.bendem.chess.pieces.Piece;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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

        for(int i = 0; i < 64; i++) {
            JLabel x = new JLabel();
            x.setPreferredSize(new Dimension(75, 75));
            x.setHorizontalAlignment(JLabel.CENTER);
            x.setVerticalAlignment(JLabel.CENTER);
            x.setOpaque(true);
            if(Board.isWhite(new Position(i % 8, i / 8))) {
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
        for(int i = 0; i < 64; i++) {
            JLabel x = list.get(i);
            Piece piece = board.get(new Position(i % 8, i / 8));
            if(piece == null) {
                x.setText("");
            } else {
                x.setForeground(piece.getColor() == be.bendem.chess.Color.Black ? Color.BLACK : Color.WHITE);
                x.setText(piece.getClass().getSimpleName().toUpperCase().substring(0, 2));
            }
        }
    }

    public JLabel get(Position position) {
        return list.get(position.getX() + position.getY());
    }

    public static void main(String[] args) {
        new UI(new Board());
    }

}
