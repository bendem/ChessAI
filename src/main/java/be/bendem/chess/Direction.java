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

    KnightLeftUp(-2, -1),
    KnightLeftDown(-2, 1),
    KnightRightUp(2, -1),
    KnightRightDown(2, 1),
    KnightUpLeft(-1, -2),
    KnightDownLeft(-1, 2),
    KnightUpRight(1, -2),
    KnightDownRight(1, 2),

    KingCastleLeft(-3, 0),
    KingCastleRight(2, 0),

    RookCastleLeft(-2, 0),
    RookCastleRight(2, 0),
    ;

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

    public boolean isLeft() {
        return x < 0;
    }

    public boolean isRight() {
        return x > 0;
    }

    public boolean isUp() {
        return y < 0;
    }

    public boolean isDown() {
        return y > 0;
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

    public static Set<Direction> getKingCastles() {
        return EnumSet.of(KingCastleLeft, KingCastleRight);
    }

    public static Set<Direction> getRookCastles() {
        return EnumSet.range(RookCastleLeft, RookCastleRight);
    }

    public static Set<Direction> getCastles() {
        Set<Direction> set = getRookCastles();
        set.addAll(getKingCastles());
        return set;
    }

}
