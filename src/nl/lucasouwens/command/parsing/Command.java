package nl.lucasouwens.command.parsing;

public class Command {

    private String command;
    private String[] requiredArguments = {}, optionalArguments = {};

    public Command(String command, String[] requiredArguments, String[] optionalArguments) {
        this.command = command;
        this.requiredArguments = requiredArguments;
        this.optionalArguments = optionalArguments;
    }

    public String getCommand() {
        return command;
    }

    public String[] getOptionalArguments() {
        return optionalArguments;
    }

    public String[] getRequiredArguments() {
        return requiredArguments;
    }

    public int getMinCommandSize() {
        return requiredArguments.length;
    }

    public static <T extends Number> T typeof(String type) {
        switch(type) {
            case "double":
                return (T) new Double(1);
            case "int":
                return (T) new Integer(1);
            case "float":
                return (T) new Float(1F);
            default:
                return null;
        }
    }

}
