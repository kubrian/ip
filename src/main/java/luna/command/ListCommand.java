package luna.command;

import java.util.ArrayList;
import java.util.stream.IntStream;

import luna.storage.Storage;
import luna.task.Task;
import luna.ui.ConsoleUi;

/**
 * Represents a command to print the current list of tasks.
 */
public class ListCommand implements Command {

    /**
     * Executes the command to print the current list of tasks.
     * <p>
     * Each task is numbered and the string is newline-separated.
     * <p>
     * Example output:
     * <pre>
     * 1: task1
     * 2: task2
     * </pre>
     */
    @Override
    public boolean execute(ConsoleUi consoleUi, Storage storage, ArrayList<Task> taskList) {
        IntStream.range(0, taskList.size())
                 .mapToObj(i -> i + 1 + ": " + taskList.get(i))
                 .forEach(consoleUi::printOutput);
        return true;
    }

}
