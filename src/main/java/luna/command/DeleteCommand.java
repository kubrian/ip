package luna.command;

import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Task;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand implements Command {

    private final int taskNumber;

    /**
     * Creates a new delete command.
     *
     * @param taskNumber The 1 indexed number of the task to delete.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to delete a task and returns the result.
     */
    @Override
    public CommandResult execute(Storage storage, ArrayList<Task> taskList) {
        Task task = taskList.remove(taskNumber - 1);
        if (storage.saveTasksToFile(taskList)) {
            return new CommandResult("Deleted task " + taskNumber + ":\n" + task, false);
        } else {
            return new CommandResult("Unable to save tasks to file", false);
        }
    }

}
