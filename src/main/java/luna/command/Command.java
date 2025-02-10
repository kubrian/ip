package luna.command;

import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Task;

/**
 * Represents a command that can be executed.
 */
@FunctionalInterface
public interface Command {

    /**
     * Executes the command.
     *
     * @param storage  The storage system for saving and loading tasks.
     * @param taskList The list of tasks to be manipulated.
     * @return A boolean indicating whether the application should continue running.
     */
    CommandResult execute(Storage storage, ArrayList<Task> taskList);

}
