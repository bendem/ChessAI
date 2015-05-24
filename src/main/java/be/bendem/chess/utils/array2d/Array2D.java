package be.bendem.chess.utils.array2d;

import be.bendem.chess.Position;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Allow easy manipulation of 2D arrays using array[column][line]
 */
public class Array2D {

    @SuppressWarnings("unchecked")
    public static <T> T[][] create(Class<T> clazz, int width, int height) {
        return (T[][]) Array.newInstance(clazz, width, height);
    }

    public static <T> T[][] copy(T[][] array) {
        @SuppressWarnings("unchecked")
        T[][] newArray = create((Class<T>) array.getClass().getComponentType(), array.length, array[0].length);

        return forEach(
            array,
            (x, y, val) -> newArray[x][y] = val
        );
    }

    public static <T> T get(T[][] array, Position position) {
        return array[position.getX()][position.getY()];
    }

    public static <T> T[][] set(T[][] array, Position position, T value) {
        array[position.getX()][position.getY()] = value;
        return array;
    }

    public static <T> T[][] forEach(T[][] array, Array2DConsumer<T> consumer) {
        for(int y = 0; y < array[0].length; ++y) {
            for(int x = 0; x < array.length; ++x) {
                consumer.accept(x, y, array[x][y]);
            }
        }
        return array;
    }

    public static <T> Stream<T> stream(T[][] array) {
        // TODO That's not right
        return Arrays.stream(array).flatMap(Arrays::stream);
    }

    public static <T> Optional<Position> searchPosition(T[][] array, Predicate<T> predicate) {
        for(int y = 0; y < array[0].length; ++y) {
            for(int x = 0; x < array.length; ++x) {
                if(array[x][y] != null && predicate.test(array[x][y])) {
                    return Optional.of(new Position(x, y));
                }
            }
        }
        return Optional.empty();
    }

    public static <T> Stream<T> lineStream(T[][] array, int line) {
        return Arrays.stream(array).map(col -> col[line]);
    }

    public static <T> Stream<T> columnStream(T[][] array, int col) {
        return Arrays.stream(array[col]);
    }

    public static <T> T[][] fillRow(T[][] array, int row, T value) {
        for(int x = 0; x < array.length; ++x) {
            array[x][row] = value;
        }
        return array;
    }

    public static <T> T[][] fillColumn(T[][] array, int col, T value) {
        for(int y = 0; y < array[0].length; ++y) {
            array[col][y] = value;
        }
        return array;
    }

    public static <T> String debug(T[][] array) {
        StringBuilder b = new StringBuilder();
        for(int y = 0; y < array[0].length; ++y) {
            for(T[] column : array) {
                b.append(column[y]).append(' ');
            }
            b.append('\n');
        }
        return b.toString();
    }

}
