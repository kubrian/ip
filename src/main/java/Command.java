import java.util.ArrayList;

@FunctionalInterface
public interface Command {
    boolean execute(ConsoleUI consoleUi, Storage storage, ArrayList<Task> taskList);
}
