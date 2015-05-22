package be.bendem.chess.utils;

import be.bendem.chess.Chess;
import be.bendem.chess.utils.timer.Part;

public class Logger {

    public enum Level {
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }

    public static void log(Level level, String str, Object...args) {
        Chess.TIMER.start(Part.Log);
        System.out.println('[' + level.name() + "] " + String.format(str, args));
        Chess.TIMER.stop();
    }

    public static void debug(String str, Object...args) {
        log(Level.DEBUG, str, args);
    }

    public static void info(String str, Object...args) {
        log(Level.INFO, str, args);
    }

    public static void warning(String str, Object...args) {
        log(Level.WARNING, str, args);
    }

    public static void error(String str, Object...args) {
        log(Level.ERROR, str, args);
    }

}
