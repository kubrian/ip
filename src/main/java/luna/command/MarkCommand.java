package luna.command;

import luna.storage.Storage;
import luna.task.Task;
import luna.ui.ConsoleUI;

import java.util.ArrayList;

public class MarkCommand implements Command {

    private final int taskNumber;

    public MarkCommand(String input) {
        this(Integer.parseInt(input));
    }

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean execute(ConsoleUI consoleUi, Storage storage, ArrayList<Task> taskList) {
        taskList.get(taskNumber - 1)
                .markAsCompleted();
        consoleUi.printOutput("Marked task " + taskNumber + " as completed");
        return true;
    }

}
