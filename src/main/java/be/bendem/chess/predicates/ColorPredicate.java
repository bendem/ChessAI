package be.bendem.chess.predicates;

import be.bendem.chess.Color;
import be.bendem.chess.pieces.Piece;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author bendem
 */
public class ColorPredicate extends PiecePredicate {

    private final Color color;

    private ColorPredicate(Color color) {
        this.color = color;
    }

    @Override
    public boolean test(Piece element) {
        return super.test(element) && element.getColor() == color;
    }

    private static final Map<Color, ColorPredicate> PREDICATES;

    static {
        Map<Color, ColorPredicate> map = new EnumMap<>(Color.class);
        for(Color color : Color.values()) {
            map.put(color, new ColorPredicate(color));
        }
        PREDICATES = Collections.unmodifiableMap(map);
    }

    public static ColorPredicate of(Color color) {
        return PREDICATES.get(color);
    }

}
