package be.bendem.chess;

import java.util.EnumSet;
import java.util.Set;

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
    RightDown(1, 1),
    KnightLeftUp(Left, Up),
    KnightLeftDown(Left, Down),
    KnightRightUp(Right, Up),
    KnightRightDown(Right, Down),
    KnightUpLeft(Up, Left),
    KnightDownLeft(Down, Left),
    KnightUpRight(Up, Right),
    KnightDownRight(Down, Right),
    ;

    private final int x;
    private final int y;

    Direction(Direction two, Direction one) {
        // TODO Check if this is right
        this.x = one.getX() + two.getX()*2;
        this.y = one.getY() + two.getY()*2;
    }

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

    public static Set<Direction> getDiagonals() {
        return EnumSet.range(LeftUp, RightDown);
    }

    public static Set<Direction> getStraights() {
        return EnumSet.range(Up, Right);
    }

    public static Set<Direction> getNormals() {
        return EnumSet.range(Up, RightDown);
    }

    public static Set<Direction> getKnights() {
        return EnumSet.range(KnightLeftUp, KnightDownRight);
    }

}
