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

    KnightLeftUp(Left, Left, Up),
    KnightLeftDown(Left, Left, Down),
    KnightRightUp(Right, Right, Up),
    KnightRightDown(Right, Right, Down),
    KnightUpLeft(Up, Up, Left),
    KnightDownLeft(Down, Down, Left),
    KnightUpRight(Up, Up, Right),
    KnightDownRight(Down, Down, Right),

    KingCastleLeft(Left, Left, Left),
    KingCastleRight(Left, Left),

    RookCastleLeft(Right, Right),
    RookCastleRight(Right, Right),
    ;

    private final int x;
    private final int y;

    Direction(Direction ...directions) {
        int x = 0;
        int y = 0;
        for(Direction direction : directions) {
            x += direction.getX();
            y += direction.getY();
        }
        this.x = x;
        this.y = y;
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
        Set<Direction> set = getStraights();
        set.addAll(getDiagonals());
        return set;
    }

    public static Set<Direction> getKnights() {
        return EnumSet.range(KnightLeftUp, KnightDownRight);
    }

}
