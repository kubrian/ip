import java.util.ArrayList;

public class UnmarkCommand implements Command {

    private final int taskNumber;

    public UnmarkCommand(String input) {
        this(Integer.parseInt(input));
    }

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean execute(ConsoleUI consoleUi, Storage storage, ArrayList<Task> taskList) {
        taskList.get(taskNumber - 1)
                .markAsNotCompleted();
        consoleUi.printOutput("Marked task " + taskNumber + " as not completed");
        return true;
    }

}
