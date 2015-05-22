package be.bendem.chess.utils.timer;

import java.util.EnumMap;
import java.util.Map;
import java.util.Stack;

public class Timer {

    private final Map<Part, Long> times;
    private final Stack<Part> previousParts;
    private long timer;
    private Part currentPart;

    public Timer() {
        times = new EnumMap<>(Part.class);
        previousParts = new Stack<>();
        timer = System.nanoTime();
        currentPart = Part.Idle;
    }

    public Timer start(Part part) {
        if(currentPart == part) {
            return this;
        }

        previousParts.add(currentPart);
        stop();
        currentPart = part;
        timer = System.nanoTime();

        return this;
    }

    public Timer stop() {
        long newTime = System.nanoTime();
        increase(currentPart, newTime - timer);
        timer = newTime;

        currentPart = previousParts.isEmpty()
            ? Part.Idle
            : previousParts.pop();

        return this;
    }

    public TimerReport report() {
        return new TimerReport(new EnumMap<>(times));
    }

    private void increase(Part part, long time) {
        times.put(part, times.getOrDefault(currentPart, 0l) + time);
        if(part.hasParent()) {
            increase(part.parent(), time);
        }
    }

}
