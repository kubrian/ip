package luna.command;

import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Task;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand implements Command {

    public static final String BYE = "Bye. Hope to see you again soon!";

    /**
     * Executes the command to exit the application and returns the result.
     */
    @Override
    public CommandResult execute(Storage storage, ArrayList<Task> taskList) {
        return new CommandResult(BYE, true);
    }

}
