package be.bendem.chess.utils.timer;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;

public class TimerReport {

    private static final DecimalFormat FORMAT = new DecimalFormat("#.###");

    public static String formatNanoSecs(long time) {
        return FORMAT.format((double) time / 1000) + " µs";
    }

    private static StringBuilder indent(StringBuilder builder, int level) {
        for(int i = 0; i < level; ++i) {
            builder.append("  ");
        }
        return builder;
    }

    private final Map<Part, Long> times;

    public TimerReport(Map<Part, Long> times) {
        this.times = times;
    }

    public String generate() {
        StringBuilder builder = new StringBuilder("| Timer Report\n| -----------------\n");

        Arrays.stream(Part.values())
            .filter(p -> !p.hasParent())
            .forEach(p -> generate(builder, p, 0));

        return builder.toString();
    }

    private void generate(StringBuilder builder, Part p, int level) {
        indent(builder.append("| "), level)
            .append(p.name())
            .append(" : ")
            .append(formatNanoSecs(times.getOrDefault(p, 0l)))
            .append('\n')
            ;

        p.children().forEach(child -> generate(builder, child, level + 1));
    }

}
