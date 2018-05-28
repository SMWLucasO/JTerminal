package nl.lucasouwens.command.parsing;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class CommandParser {

    /**
     *
     */
    private static CommandParser instance = null;

    public static CommandParser getInstance() {
        if (instance == null) {
            instance = new CommandParser();
        }

        return instance;
    }

    public boolean parse(Command command, Method method) {
        if (CommandRegister.getInstance().getRegister().containsKey(command)) {
            return true;
        } else {
            boolean hasCommandType = false;
            for (Annotation methodAnnotation : method.getAnnotations()) {
                if (methodAnnotation instanceof CommandType) {
                    hasCommandType = true;
                    break;
                }
            }

            if (hasCommandType) {
                CommandRegister.getInstance().register(command, method);
                return true;
            }
        }
        return false;
    }

}
