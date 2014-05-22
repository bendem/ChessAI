package be.bendem.chess;

/**
 * @author bendem
 */
public enum Direction {
    Up(0, -1),
    Down(0, 1),
    Left(-1, 0),
    Right(1, 0),
    LeftUp(-1, -1),
    LeftDown(-1, 1),
    RightUp(1, -1),
    RightDown(1, 1);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Direction[] getDiagonals() {
        return new Direction[] {
            LeftDown, LeftUp, RightDown, RightUp
        };
    }

    public static Direction[] getStraights() {
        return new Direction[] {
            Up, Down, Right, Left
        };
    }

}
