package be.bendem.chess;

import junit.framework.TestCase;

public class BoardTest extends TestCase {

    private boolean[][] correctBoard;

    public void setUp() throws Exception {
        correctBoard = new boolean[8][8];
        boolean current = true;
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                correctBoard[x][y] = current;
                current = !current;
            }
            current = !current;
        }
    }

    public void testIsWhite() throws Exception {
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                assertEquals("["+x+"]["+y+"]", correctBoard[x][y], Board.isWhite(new Coordinates(x, y)));
            }
        }
    }

    public void testIsBlack() throws Exception {
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                assertEquals("["+x+"]["+y+"]", correctBoard[x][y], !Board.isBlack(new Coordinates(x, y)));
            }
        }
    }

}
