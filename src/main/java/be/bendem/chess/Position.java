package be.bendem.chess;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author bendem
 */
public class Position {

    private static final String NO_POINTING_OUTSIDE_THE_BOARD = "No pointing outside the board";

    private int x;
    private int y;

    public Position() {
        this(0, 0);
    }

    public Position(Position position) {
        this(position.getX(), position.getY());
    }

    public Position(Position position, Direction direction) {
        this(position.getX(), position.getY());
        add(direction);
    }

    public Position(int x, int y) {
        if(overflow(x, y)) {
            throw new IllegalArgumentException(NO_POINTING_OUTSIDE_THE_BOARD);
        }
        this.x = x;
        this.y = y;
    }

    public void add(Direction direction) {
        if(overflow(this, direction)) {
            throw new IllegalArgumentException(NO_POINTING_OUTSIDE_THE_BOARD);
        }
        x += direction.getX();
        y += direction.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        if(overflowX(x)) {
            throw new IllegalArgumentException(NO_POINTING_OUTSIDE_THE_BOARD);
        }
        this.x = x;
    }

    public void setY(int y) {
        if(overflowY(y)) {
            throw new IllegalArgumentException(NO_POINTING_OUTSIDE_THE_BOARD);
        }
        this.y = y;
    }

    public void set(Position position) {
        this.x = position.x;
        this.y = position.y;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return x << 4 & y;
    }

    public static boolean overflowX(int x) {
        return x < 0 || x >= Board.WIDTH;
    }

    public static boolean overflowY(int y) {
        return y < 0 || y >= Board.HEIGHT;
    }

    public static boolean overflow(int x, int y) {
        return overflowX(x) || overflowY(y);
    }

    public static boolean overflow(int x, int y, Direction direction) {
        return overflow(x + direction.getX(), y + direction.getY());
    }

    public static boolean overflow(Position position, Direction direction) {
        return overflow(position.getX(), position.getY(), direction);
    }

}
