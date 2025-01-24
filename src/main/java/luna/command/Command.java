package luna.command;

import luna.storage.Storage;
import luna.task.Task;
import luna.ui.ConsoleUI;

import java.util.ArrayList;

@FunctionalInterface
public interface Command {

    boolean execute(ConsoleUI consoleUi, Storage storage, ArrayList<Task> taskList);

}
