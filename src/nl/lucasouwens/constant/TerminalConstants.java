package nl.lucasouwens.constant;

import nl.lucasouwens.logger.MessageType;

public class TerminalConstants {

    /**
     * This defines the minimal level of logging, anything below this will not be shown.
     *
     * ALL will always be shown.
     */
    public static final MessageType minimalLogLevel = MessageType.ALL;

    /**
     * The prefix for message logging
     */
    public static final String PREFIX = "[JTerminal]";

    /**
     * The folder where all modules are located
     */
    public static final String MODULE_FOLDER = "modules/";

    /**
     * The name of the file where the properties for loading the file are located
     */
    public static final String MODULE_PROPERTY_LOCATION = "module.properties";

}
