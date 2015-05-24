package be.bendem.chess.utils;

import java.util.ArrayList;
import java.util.stream.Stream;

public class ArrayList2D<T> {

    private final ArrayList<ArrayList<T>> columns;
    private final int width;
    private final int height;

    public ArrayList2D(int width, int height) {
        this.columns = new ArrayList<>(width);
        this.width = width;
        this.height = height;

        for(int i = 0; i < height; ++i) {
            ArrayList<T> column = new ArrayList<>(height);
            for(int j = 0; j < width; ++j) {
                column.add(null);
            }
            columns.add(column);
        }
    }

    public T get(int x, int y) {
        return columns.get(x).get(y);
    }

    public ArrayList2D<T> set(int x, int y, T val) {
        columns.get(x).set(y, val);
        return this;
    }

    public boolean isEmpty(int x, int y) {
        return get(x, y) == null;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public Stream<T> stream() {
        return columns.stream().flatMap(ArrayList::stream);
    }

}
