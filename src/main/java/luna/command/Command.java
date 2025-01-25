package luna.command;

import java.util.ArrayList;

import luna.storage.Storage;
import luna.task.Task;
import luna.ui.ConsoleUi;

@FunctionalInterface
public interface Command {

    boolean execute(ConsoleUi consoleUi, Storage storage, ArrayList<Task> taskList);

}
