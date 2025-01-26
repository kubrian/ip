package luna.command;

import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Task;
import luna.task.Todo;
import luna.ui.ConsoleUi;

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
    public boolean execute(ConsoleUi consoleUi, Storage storage, ArrayList<Task> taskList) {
        Task task = new Todo(description);
        taskList.add(task);
        consoleUi.printOutput(String.format("Added new todo:\n%s", task));
        return true;
    }

}
