package be.bendem.chess.utils.array2d;

public interface Array2DConsumer<T> {

    void accept(int x, int y, T value);

}
