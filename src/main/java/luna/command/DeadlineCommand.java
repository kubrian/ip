package luna.command;

import java.time.LocalDateTime;
import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Deadline;
import luna.task.Task;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand implements Command {

    private final String description;
    private final LocalDateTime by;

    /**
     * Creates a new deadline command.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task.
     */
    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command to add a deadline task and returns the result.
     */
    @Override
    public CommandResult execute(Storage storage, ArrayList<Task> taskList) {
        Task task = new Deadline(description, by);
        taskList.add(task);
        if (storage.saveTasksToFile(taskList)) {
            return new CommandResult("Added new deadline:\n" + task, false);
        } else {
            return new CommandResult("Unable to save tasks to file", false);
        }
    }

}
