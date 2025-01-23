import java.util.ArrayList;

public class ByeCommand implements Command {
    public boolean execute(ConsoleUI consoleUi, Storage storage, ArrayList<Task> taskList) {
        consoleUi.goodbye();
        return false;
    }
}