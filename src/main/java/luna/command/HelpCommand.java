package luna.command;

import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Task;
import luna.ui.ConsoleUi;

/**
 * Represents a command to display the help text.
 */
public class HelpCommand implements Command {

    /**
     * Executes the command to display the help text.
     */
    @Override
    public boolean execute(ConsoleUi consoleUi, Storage storage, ArrayList<Task> taskList) {
        consoleUi.printOutput(Operation.helpString);
        return true;
    }

}
