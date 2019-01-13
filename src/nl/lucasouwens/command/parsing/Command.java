package nl.lucasouwens.command.parsing;

public class Command {

    private String command;
    private String[] requiredArguments, optionalArguments;

    /**
     * The constructor for the command.
     * @param command String The name which you call the command by
     * @param requiredArguments String[] The required arguments for the command
     * @param optionalArguments String[] The optional requirements for the command
     */
    public Command(String command, String[] requiredArguments, String[] optionalArguments) {
        this.command = command;
        this.requiredArguments = requiredArguments;
        this.optionalArguments = optionalArguments;
    }

    /**
     * Command name
     * @return String
     */
    public String getCommand() {
        return command;
    }

    /**
     * Optional command arguments
     * @return String[]
     */
    public String[] getOptionalArguments() {
        return optionalArguments;
    }

    /**
     * Required command arguments
     * @return String[]
     */
    public String[] getRequiredArguments() {
        return requiredArguments;
    }

    /**
     * Minimal amount of arguments for the command
     * @return int
     */
    public int getMinCommandSize() {
        return requiredArguments.length;
    }

}
