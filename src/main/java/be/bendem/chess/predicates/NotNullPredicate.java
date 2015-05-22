package be.bendem.chess.predicates;

import java.util.function.Predicate;

public class NotNullPredicate<T> implements Predicate<T> {

    private static final NotNullPredicate INSTANCE = new NotNullPredicate<>();

    @SuppressWarnings("unchecked")
    public static <T> NotNullPredicate<T> get() {
        return (NotNullPredicate<T>) INSTANCE;
    }

    private NotNullPredicate() {}

    @Override
    public boolean test(T element) {
        return element != null;
    }

}
