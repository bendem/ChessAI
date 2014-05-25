package be.bendem.chess;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author bendem
 */
public class Coordinates implements Cloneable {

    private int x;
    private int y;

    public Coordinates() {
        this(0, 0);
    }

    public Coordinates(Coordinates coordinates, Direction direction) {
        x = coordinates.getX();
        y = coordinates.getY();
        add(direction);
    }

    public Coordinates(int x, int y) {
        if(overflow(x, y)) {
            throw new IllegalArgumentException("No pointing outside the board");
        }
        this.x = x;
        this.y = y;
    }

    public void add(Direction direction) {
        if(overflow(this, direction)) {
            throw new IllegalArgumentException("No pointing outside the board");
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
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public final Coordinates clone() {
        try {
            return (Coordinates) super.clone();
        } catch(CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static boolean overflow(int x, int y) {
        return x < 0 || y < 0 || x > 7 || y > 7;
    }

    public static boolean overflow(int x, int y, Direction direction) {
        return overflow(x + direction.getX(), y + direction.getY());
    }

    public static boolean overflow(Coordinates coordinates, Direction direction) {
        return overflow(coordinates.getX(), coordinates.getY(), direction);
    }

}
