package be.bendem.chess.utils.timer;

import java.util.EnumMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author bendem
 */
public class Timer {

    private static final Map<Part, Long> times = new EnumMap<>(Part.class);
    private static final Stack<Part> previousParts = new Stack<>();
    private static long timer = System.nanoTime();
    private static Part currentPart = Part.Idle;

    public static void start(Part part) {
        previousParts.add(currentPart);
        stop();
        currentPart = part;
        timer = System.nanoTime();
    }

    public static void stop() {
        long newTime = System.nanoTime();
        increase(currentPart, newTime - timer);
        timer = newTime;

        currentPart = previousParts.isEmpty()
            ? Part.Idle
            : previousParts.pop();
    }

    public static TimerReport report() {
        return new TimerReport(times);
    }

    private static void increase(Part part, long time) {
        times.put(part, times.getOrDefault(currentPart, 0l) + time);
        if(part.hasParent()) {
            increase(part.parent(), time);
        }
    }

}
