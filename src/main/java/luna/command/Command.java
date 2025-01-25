package luna.command;

import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Task;
import luna.ui.ConsoleUi;

/**
 * Represents a command that can be executed.
 */
@FunctionalInterface
public interface Command {

    /**
     * Executes the command.
     *
     * @param consoleUi The user interface for interacting with the user.
     * @param storage   The storage system for saving and loading tasks.
     * @param taskList  The list of tasks to be manipulated.
     * @return A boolean indicating whether the application should continue running.
     */
    boolean execute(ConsoleUi consoleUi, Storage storage, ArrayList<Task> taskList);

}