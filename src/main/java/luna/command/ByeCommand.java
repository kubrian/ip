package luna.command;

import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Task;
import luna.ui.ConsoleUi;

public class ByeCommand implements Command {

    public boolean execute(ConsoleUi consoleUi, Storage storage, ArrayList<Task> taskList) {
        consoleUi.goodbye();
        return false;
    }

}