package be.bendem.chess.filter;

/**
 * @author bendem
 */
public interface Filter<E> {

    public boolean keep(E element);

}
