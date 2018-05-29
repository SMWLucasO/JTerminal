package nl.lucasouwens.loader;

/**
 * Interface for registering external commands
 * @see nl.lucasouwens.command.Commands on how to implement your own commands.
 */
public interface TerminalCommandExecutor {

    /**
     * Register your commands through this method
     */
    public void register();

}
