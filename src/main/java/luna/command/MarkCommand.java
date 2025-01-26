package luna.command;

import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Task;
import luna.ui.ConsoleUi;

/**
 * Represents a command to mark a task as completed.
 */
public class MarkCommand implements Command {

    private final int taskNumber;

    /**
     * Creates a new mark command.
     *
     * @param taskNumber The 1 indexed number of the task to mark as completed.
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to mark a task as completed.
     */
    @Override
    public boolean execute(ConsoleUi consoleUi, Storage storage, ArrayList<Task> taskList) {
        taskList.get(taskNumber - 1)
                .markAsCompleted();
        consoleUi.printOutput("Marked task " + taskNumber + " as completed");
        storage.saveTasksToFile(consoleUi, taskList);
        return true;
    }

}
