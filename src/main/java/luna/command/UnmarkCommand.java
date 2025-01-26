package luna.command;

import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Task;
import luna.ui.ConsoleUi;

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
     * Executes the command to mark a task as not completed.
     */
    @Override
    public boolean execute(ConsoleUi consoleUi, Storage storage, ArrayList<Task> taskList) {
        taskList.get(taskNumber - 1)
                .markAsNotCompleted();
        consoleUi.printOutput("Marked task " + taskNumber + " as not completed");
        storage.saveTasksToFile(consoleUi, taskList);
        return true;
    }

}
