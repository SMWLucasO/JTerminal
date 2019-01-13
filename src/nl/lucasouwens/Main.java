package nl.lucasouwens;

import nl.lucasouwens.command.parsing.CommandParser;
import nl.lucasouwens.command.parsing.CommandRegister;
import nl.lucasouwens.command.Commands;
import nl.lucasouwens.loader.TerminalCommandLoader;
import nl.lucasouwens.logger.Logger;
import nl.lucasouwens.logger.MessageType;

import java.util.Scanner;

/**
 * Run the terminal.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CommandRegister.getInstance().register(Commands.class);
        TerminalCommandLoader.getInstance().load();

        String line;
        while((line = sc.nextLine()) != null && !line.equalsIgnoreCase("q")) {
            if(!(CommandParser.getInstance().execute(line))) {
                Logger.log("Failed to execute the specified command", MessageType.ERROR);
            }
        }
    }

}