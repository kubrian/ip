import java.util.ArrayList;
import java.util.stream.IntStream;

public class ListCommand implements Command {

    /**
     * Executes the command to print the current list of tasks.
     * <p>
     * Each item is numbered and the string is newline-separated.
     * <p>
     * Example output:
     * <pre>
     * 1: item1
     * 2: item2
     * </pre>
     *
     * @param consoleUi The user interface to print the task list.
     * @param storage   The storage for tasks (not used in this command).
     * @param taskList  The list of tasks to be printed.
     * @return Always returns true.
     * @inheritDoc
     */
    public boolean execute(ConsoleUI consoleUi, Storage storage, ArrayList<Task> taskList) {
        IntStream.range(0, taskList.size())
                 .mapToObj(i -> i + 1 + ": " + taskList.get(i))
                 .forEach(consoleUi::printOutput);
        return true;
    }

}
