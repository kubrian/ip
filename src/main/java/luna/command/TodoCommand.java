package luna.command;

import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Task;
import luna.task.Todo;
import luna.ui.ConsoleUi;

public class TodoCommand implements Command {

    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public boolean execute(ConsoleUi consoleUi, Storage storage, ArrayList<Task> taskList) {
        Task task = new Todo(description);
        taskList.add(task);
        consoleUi.printOutput(String.format("Added new todo:\n%s", task));
        return true;
    }

}
