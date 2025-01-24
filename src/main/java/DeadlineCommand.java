import java.time.LocalDateTime;
import java.util.ArrayList;

public class DeadlineCommand implements Command {

    private final String description;
    private final LocalDateTime by;

    public DeadlineCommand(String description, LocalDateTime by) {
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
