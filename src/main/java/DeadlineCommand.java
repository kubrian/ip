import java.util.ArrayList;

public class DeadlineCommand implements Command {

    private final String description;
    private final String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public boolean execute(ConsoleUI consoleUi, Storage storage, ArrayList<Task> taskList) {
        Task task = new Deadline(description, by);
        taskList.add(task);
        consoleUi.printOutput("Added new deadline:\n" + task);
        return true;
    }

}
