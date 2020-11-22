package infrastructure;

import org.slf4j.LoggerFactory;
import ports.Logger;
import ports.LoggingType;

public class ConsoleInfoLogger implements Logger {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ConsoleInfoLogger.class);

    ConsoleInfoLogger() {

    }

    @Override
    public void log(final LoggingType type, final String message) {
        logger.info(message);
    }
}
