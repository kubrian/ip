package luna.command;

import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Task;
import luna.ui.ConsoleUi;

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
     * Executes the command to delete a task.
     */
    @Override
    public boolean execute(ConsoleUi consoleUi, Storage storage, ArrayList<Task> taskList) {
        Task task = taskList.remove(taskNumber - 1);
        consoleUi.printOutput("Deleted task " + taskNumber + ":\n" + task);
        return true;
    }

}
