package luna.command;

import java.time.LocalDateTime;
import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Event;
import luna.task.Task;
import luna.ui.ConsoleUi;

/**
 * Represents a command to add an event task.
 */
public class EventCommand implements Command {

    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs a new event command.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command to add an event task.
     */
    @Override
    public boolean execute(ConsoleUi consoleUi, Storage storage, ArrayList<Task> taskList) {
        Task task = new Event(description, from, to);
        taskList.add(task);
        consoleUi.printOutput(String.format("Added new event:\n%s", task));
        return true;
    }

}
