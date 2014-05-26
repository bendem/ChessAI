package be.bendem.chess.utils;

import java.text.DecimalFormat;

/**
 * @author bendem
 */
public class Timer {

    private static final DecimalFormat FORMAT = new DecimalFormat("#.###");
    private static long timer;

    // TODO This class will eventually be used in the all project (not statically anymore)
    // but as a static instance to provide a full report of the timings of a game iterations,
    // allowing dev to analyze the ai and improve the slower bits

    public static void startNanoTimer() {
        timer = System.nanoTime();
    }

    public static long stopNanoTimer() {
        return System.nanoTime() - timer;
    }

    public static String formatNanoSecs(long time) {
        return FORMAT.format((double)time / 1000) + " µs";
    }

}
