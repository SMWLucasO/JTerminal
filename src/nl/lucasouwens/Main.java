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
        do {
            if (sc.hasNext()) {
                CommandParser.getInstance().execute(sc.nextLine());
            }
        } while (!(sc.nextLine().equalsIgnoreCase("q")));
    }

}