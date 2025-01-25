package luna.command;

import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Task;
import luna.ui.ConsoleUi;

public class HelpCommand implements Command {

    public boolean execute(ConsoleUi consoleUi, Storage storage, ArrayList<Task> taskList) {
        consoleUi.printOutput(Operation.helpString);
        return true;
    }

}
