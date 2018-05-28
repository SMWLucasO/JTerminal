package nl.lucasouwens.command.parsing;

import java.lang.reflect.Method;
import java.util.HashMap;

public class CommandRegister {

    private static CommandRegister instance = null;
    private HashMap<Command, Method> commandRegister = null;

    private CommandRegister() {
        commandRegister = new HashMap<>();
    }

    public static CommandRegister getInstance() {
        if(instance == null) {
            instance = new CommandRegister();
        }
        return instance;
    }

    public void register(Command command, Method method) {
        commandRegister.put(command, method);
    }

    public HashMap<Command, Method> getRegister() {
        return commandRegister;
    }

    public static Command getCommandByName(String command) {
        for (Command c : CommandRegister.getInstance().getRegister().keySet()) {
            if(c.getCommand().equalsIgnoreCase(command)) {
                return c;
            }
        }

        return null;
    }
}
