package luna.command;

import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Task;
import luna.ui.ConsoleUi;

public class UnmarkCommand implements Command {

    private final int taskNumber;

    public UnmarkCommand(String input) {
        this(Integer.parseInt(input));
    }

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean execute(ConsoleUi consoleUi, Storage storage, ArrayList<Task> taskList) {
        taskList.get(taskNumber - 1)
                .markAsNotCompleted();
        consoleUi.printOutput("Marked task " + taskNumber + " as not completed");
        return true;
    }

}
