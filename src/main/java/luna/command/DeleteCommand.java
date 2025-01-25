package luna.command;

import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Task;
import luna.ui.ConsoleUi;

public class DeleteCommand implements Command {

    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean execute(ConsoleUi consoleUi, Storage storage, ArrayList<Task> taskList) {
        Task task = taskList.remove(taskNumber - 1);
        consoleUi.printOutput("Deleted task " + taskNumber + ":\n" + task);
        storage.saveTasksToFile(consoleUi, taskList);
        return true;
    }

}
