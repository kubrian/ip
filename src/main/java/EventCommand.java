import java.util.ArrayList;

public class EventCommand implements Command {

    private final String description;
    private final String from;
    private final String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean execute(ConsoleUI consoleUi, Storage storage, ArrayList<Task> taskList) {
        Task task = new Event(description, from, to);
        taskList.add(task);
        consoleUi.printOutput(String.format("Added new event:\n%s", task));
        return true;
    }

}
