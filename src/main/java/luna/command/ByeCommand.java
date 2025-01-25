package luna.command;

import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Task;
import luna.ui.ConsoleUi;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand implements Command {

    /**
     * Executes the command to exit the application.
     */
    @Override
    public boolean execute(ConsoleUi consoleUi, Storage storage, ArrayList<Task> taskList) {
        consoleUi.goodbye();
        return false;
    }

}