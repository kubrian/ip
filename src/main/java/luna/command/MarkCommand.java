package luna.command;

import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Task;
import luna.ui.ConsoleUi;

public class MarkCommand implements Command {

    private final int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean execute(ConsoleUi consoleUi, Storage storage, ArrayList<Task> taskList) {
        taskList.get(taskNumber - 1)
                .markAsCompleted();
        consoleUi.printOutput("Marked task " + taskNumber + " as completed");
        storage.saveTasksToFile(consoleUi, taskList);
        return true;
    }

}
