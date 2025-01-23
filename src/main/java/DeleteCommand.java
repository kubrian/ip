import java.util.ArrayList;

public class DeleteCommand implements Command {

    private final int taskNumber;

    public DeleteCommand(String input) {
        taskNumber = Integer.parseInt(input);
    }

    @Override
    public boolean execute(ConsoleUI consoleUi, Storage storage, ArrayList<Task> taskList) {
        Task task = taskList.remove(taskNumber - 1);
        consoleUi.printOutput("Deleted task " + taskNumber + ":\n" + task);
        return true;
    }

}
