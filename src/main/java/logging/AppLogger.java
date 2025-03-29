package logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppLogger {
    private static final Logger LOGGER = Logger.getLogger(AppLogger.class.getName());

    static {
        // Remove all existed handlers
        for (Handler handler : LOGGER.getHandlers()) {
            LOGGER.removeHandler(handler);
        }

        // Turn OFF parent handlers
        LOGGER.setUseParentHandlers(false);

        // Create new custom handler
        ConsoleHandler handler = new ConsoleHandler();

        // Set simple format of output
        handler.setFormatter(new SimpleFormatter() {
            private static final String RESET = "\u001B[0m";
            private static final String COLOR = "\u001B[97m"; // white

            @Override
            public String format(LogRecord logRecord) {
                // only colored message
                return COLOR + logRecord.getMessage() + RESET + "\n";
            }
        });

        // Add handler
        LOGGER.addHandler(handler);
    }

    private AppLogger() {
    }

    public static void log(String message) {
        LOGGER.info(message);
    }
}
