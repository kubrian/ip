package luna.command;

import java.time.LocalDateTime;
import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Event;
import luna.task.Task;
import luna.ui.ConsoleUi;

public class EventCommand implements Command {

    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean execute(ConsoleUi consoleUi, Storage storage, ArrayList<Task> taskList) {
        Task task = new Event(description, from, to);
        taskList.add(task);
        consoleUi.printOutput(String.format("Added new event:\n%s", task));
        storage.saveTasksToFile(consoleUi, taskList);
        return true;
    }

}
