package luna.command;

import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Task;
import luna.task.Todo;

/**
 * Represents a command to add a todo task.
 */
public class TodoCommand implements Command {

    private final String description;

    /**
     * Creates a new todo command.
     *
     * @param description The description of the task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add a todo task.
     */
    @Override
    public CommandResult execute(Storage storage, ArrayList<Task> taskList) {
        Task task = new Todo(description);
        taskList.add(task);
        if (storage.saveTasksToFile(taskList)) {
            return new CommandResult("Added new todo:\n" + task, false);
        } else {
            return new CommandResult("Unable to save tasks to file", false);
        }
    }

}
