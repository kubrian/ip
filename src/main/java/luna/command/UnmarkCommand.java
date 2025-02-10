package luna.command;

import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Task;

/**
 * Represents a command to mark a task as not completed.
 */
public class UnmarkCommand implements Command {

    private final int taskNumber;

    /**
     * Creates a new unmark command.
     *
     * @param taskNumber The 1 indexed number of the task to mark as not completed.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to mark a task as not completed and returns the result.
     */
    @Override
    public CommandResult execute(Storage storage, ArrayList<Task> taskList) {
        taskList.get(taskNumber - 1)
                .markAsNotCompleted();
        if (storage.saveTasksToFile(taskList)) {
            return new CommandResult("Marked task " + taskNumber + " as completed", false);
        } else {
            return new CommandResult("Unable to save tasks to file", false);

        }
    }

}
