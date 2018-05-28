package nl.lucasouwens;

import nl.lucasouwens.command.parsing.Command;
import nl.lucasouwens.command.parsing.CommandRegister;
import nl.lucasouwens.command.parsing.CommandType;
import nl.lucasouwens.command.Commands;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Class commands = Commands.class;
        for (Method method : commands.getDeclaredMethods()) {
            for (Annotation annotation : method.getDeclaredAnnotations()) {
                if (annotation instanceof CommandType) {
                    System.out.println("Registered command " + ((CommandType) annotation).name());
                    CommandRegister.getInstance().register(new Command(((CommandType) annotation).name(), ((CommandType) annotation).args(), ((CommandType) annotation).optional()), method);
                }
            }
        }
        do {
            if (sc.hasNext()) {
                String[] commandPieces = sc.nextLine().split(" ");

                if(commandPieces.length >= 1) {
                    Command commandToExecute = CommandRegister.getCommandByName(commandPieces[0]);
                    Method method = CommandRegister.getInstance().getRegister().get(CommandRegister.getCommandByName(commandPieces[0]));
                    if (method != null) {
                        try {
                            String[] arguments = Arrays.copyOfRange(commandPieces, 1, commandPieces.length-1 <= method.getParameterCount() ? commandPieces.length : method.getParameterCount()+1);

                            if(arguments.length >= commandToExecute.getMinCommandSize()) {
                                if(commandPieces.length <= method.getParameterCount()) {
                                    for(int i = commandPieces.length; i <= method.getParameterCount(); i++) {
                                        commandPieces[i] = null;
                                    }
                                }

                                method.invoke(Commands.getInstance(), arguments);
                            } else {
                                System.out.print(String.format("[Lucas' Terminal] The command %s requires atleast %d arguments.", commandToExecute.getCommand(), commandToExecute.getMinCommandSize()));
                            }
                        } catch (SecurityException | IllegalAccessException | InvocationTargetException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        } while (!(sc.nextLine().equalsIgnoreCase("q")));
    }

    private static String[] join(String[]... arrays) {
        // calculate size of target array
        int size = 0;
        for (String[] array : arrays) {
            size += array.length;
        }

        // create list of appropriate size
        java.util.List list = new java.util.ArrayList(size);

        // add arrays
        for (String[] array : arrays) {
            list.addAll(java.util.Arrays.asList(array));
        }

        // create and return final array
        return (String[]) list.toArray(new String[size]);
    }

}
