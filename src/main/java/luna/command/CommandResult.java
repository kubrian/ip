package luna.command;

/**
 * Represents the result of executing a command.
 */
public class CommandResult {

    private final String output;
    private final boolean isExit;

    /**
     * Constructs a new CommandResult.
     *
     * @param output The output of executing the command.
     * @param isExit Whether to exit the application.
     */
    public CommandResult(String output, boolean isExit) {
        this.output = output;
        this.isExit = isExit;
    }

    /**
     * Returns the output of executing the command.
     */
    public String getOutput() {
        return output;
    }

    /**
     * Returns whether to exit the application.
     */
    public boolean isExit() {
        return isExit;
    }

}
