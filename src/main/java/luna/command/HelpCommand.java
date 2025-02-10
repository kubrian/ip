package luna.command;

import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Task;

/**
 * Represents a command to display the help text.
 */
public class HelpCommand implements Command {

    /**
     * Executes the command to display the help text and returns the result.
     */
    @Override
    public CommandResult execute(Storage storage, ArrayList<Task> taskList) {
        return new CommandResult(Operation.HELP_STRING, false);
    }

}
