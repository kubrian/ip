package luna.command;

import luna.storage.Storage;
import luna.task.Task;
import luna.task.ToDo;
import luna.ui.ConsoleUI;

import java.util.ArrayList;

public class TodoCommand implements Command {

    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public boolean execute(ConsoleUI consoleUi, Storage storage, ArrayList<Task> taskList) {
        Task task = new ToDo(description);
        taskList.add(task);
        consoleUi.printOutput(String.format("Added new todo:\n%s", task));
        return true;
    }

}
