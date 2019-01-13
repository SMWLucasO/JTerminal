package nl.lucasouwens.logger;

import nl.lucasouwens.constant.TerminalConstants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    /**
     * Log messages depending on their logging level (Minimal level defined in the TerminalConstants file)
     * ALL = always shown
     * @param message String the message you want to show
     * @param type MessageType the importance of the message (and of what type it is)
     */
    public static void log(String message, MessageType type) {
        if (TerminalConstants.minimalLogLevel.ID <= type.ID || TerminalConstants.minimalLogLevel.equals(MessageType.ALL)) {
            System.out.println("[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("d-M-u H:m:s")) + "]" + TerminalConstants.PREFIX + " " + type.TEXT + ": " + message);
        }
    }

    public static void log(String message) {
        log(message, MessageType.ALL);
    }

}
