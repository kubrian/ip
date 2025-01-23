import java.util.ArrayList;

public class MarkCommand implements Command {

    private final int taskNumber;

    public MarkCommand(String input) {
        this.taskNumber = Integer.parseInt(input);
    }

    @Override
    public boolean execute(ConsoleUI consoleUi, Storage storage, ArrayList<Task> taskList) {
        taskList.get(taskNumber - 1)
                .markAsCompleted();
        consoleUi.printOutput("Marked task " + taskNumber + " as completed");
        return true;
    }

}
