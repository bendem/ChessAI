package be.bendem.chess;

/**
 * @author bendem
 */
public class Coordinates {

    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        if(x > 7 || x < 0 || y > 7 || y < 0) {
            throw new IllegalArgumentException("No pointing outside the board");
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
