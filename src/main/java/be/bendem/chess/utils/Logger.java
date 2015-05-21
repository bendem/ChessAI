package be.bendem.chess.utils;

public class Logger {

    public enum Level {
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }

    public static void log(Level level, String str, Object...args) {
        System.out.println('[' + level.name() + "] " + String.format(str, args));
    }

    public static void debug(String str, Object...args) {
        log(Level.DEBUG, str, args);
    }

    public static void info(String str, Object...args) {
        log(Level.INFO, str);
    }

    public static void warning(String str, Object...args) {
        log(Level.WARNING, str, args);
    }

    public static void error(String str, Object...args) {
        log(Level.ERROR, str, args);
    }

}
