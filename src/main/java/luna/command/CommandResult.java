package luna.command;

/**
 * Represents the result of executing a command.
 */
public class CommandResult {

    private final String output;
    private final boolean exit;

    /**
     * Constructs a new CommandResult.
     *
     * @param output The output of executing the command.
     * @param exit   Whether to exit the application.
     */
    public CommandResult(String output, boolean exit) {
        this.output = output;
        this.exit = exit;
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
        return exit;
    }

}
