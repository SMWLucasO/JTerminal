package nl.lucasouwens;

import nl.lucasouwens.command.parsing.CommandParser;
import nl.lucasouwens.command.parsing.CommandRegister;
import nl.lucasouwens.command.Commands;
import nl.lucasouwens.loader.TerminalCommandLoader;

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
                System.out.println("[Lucas' Terminal] Failed to execute the specified command");
            }
        }
    }

}