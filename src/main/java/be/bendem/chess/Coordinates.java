package be.bendem.chess;

/**
 * @author bendem
 */
public class Coordinates {

    private int x;
    private int y;

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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
