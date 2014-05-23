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
    LeftUp(Left, Up),
    LeftDown(Left, Down),
    RightUp(Right, Up),
    RightDown(Right, Down),
    KnightLeftUp(Left, Up, true),
    KnightLeftDown(Left, Down, true),
    KnightRightUp(Right, Up, true),
    KnightRightDown(Right, Down, true),
    KnightUpLeft(Up, Left, true),
    KnightDownLeft(Down, Left, true),
    KnightUpRight(Up, Right, true),
    KnightDownRight(Down, Right, true),
    ;

    private final int x;
    private final int y;

    Direction(Direction one, Direction two) {
        this(one, two, false);
    }

    Direction(Direction one, Direction two, boolean firstCountTwice) {
        // TODO Check if this is right
        this.x = one.getX() * (firstCountTwice ? 2 : 1) + two.getX();
        this.y = one.getY() * (firstCountTwice ? 2 : 1) + two.getY();
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
