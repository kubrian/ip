package luna.command;

import luna.storage.Storage;
import luna.task.Task;
import luna.ui.ConsoleUI;

import java.util.ArrayList;

public class HelpCommand implements Command {

    public boolean execute(ConsoleUI consoleUi, Storage storage, ArrayList<Task> taskList) {
        consoleUi.printOutput(Operation.helpString);
        return true;
    }

}
