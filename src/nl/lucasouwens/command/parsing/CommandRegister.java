package nl.lucasouwens.command.parsing;

import nl.lucasouwens.logger.Logger;
import nl.lucasouwens.logger.MessageType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;

public class CommandRegister {

    private static CommandRegister instance = null;
    private HashMap<Command, Method> commandRegister;

    private CommandRegister() {
        commandRegister = new HashMap<>();
    }

    /**
     * Get access to the singleton CommandRegister class
     * @return CommandRegister
     */
    public static CommandRegister getInstance() {
        if(instance == null) {
            instance = new CommandRegister();
        }
        return instance;
    }


    /**
     * Register all the commands which are contained within a specific class.
     * Make sure that you are using the CommandType annotation to register these new commands.
     * @see CommandType
     * @param functionalityClass Class
     */
    public void register(Class functionalityClass) {
        for (Method method : functionalityClass.getDeclaredMethods()) {
            for (Annotation annotation : method.getDeclaredAnnotations()) {
                if (annotation instanceof CommandType) {
                    Logger.log(String.format("Registered command %s from %s", ((CommandType) annotation).name(), functionalityClass.getSimpleName()), MessageType.INFO);
                    getRegister().put(new Command(((CommandType) annotation).name(), ((CommandType) annotation).args(), ((CommandType) annotation).optional()), method);
                }
            }
        }
    }

    /**
     * A store of all commands witin the terminal.
     *
     * @return HashMap
     */
    public HashMap<Command, Method> getRegister() {
        return commandRegister;
    }

    /**
     * Find a specific command within the list.
     * @param command String The command which you are looking for
     * @return Command
     * @see Command
     */
    public static Command getCommandByName(String command) {
        for (Command c : CommandRegister.getInstance().getRegister().keySet()) {
            if(c.getCommand().equalsIgnoreCase(command)) {
                return c;
            }
        }
        return null;
    }
}
