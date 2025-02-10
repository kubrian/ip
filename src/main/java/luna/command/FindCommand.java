package luna.command;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import luna.storage.Storage;
import luna.task.Task;

/**
 * Represents a command to find tasks in the task list that contain a keyword.
 */
public class FindCommand implements Command {

    private final String keyword;

    /**
     * Creates a new find command.
     *
     * @param keyword The keyword to search for in the task list
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks in the task list that contain a keyword and returns the
     * result.
     */
    @Override
    public CommandResult execute(Storage storage, ArrayList<Task> taskList) {
        String result = IntStream.range(0, taskList.size())
                                 .filter(i -> taskList.get(i)
                                                      .getDescription()
                                                      .contains(keyword))
                                 .mapToObj(i -> i + 1 + ": " + taskList.get(i))
                                 .collect(Collectors.joining("\n"));

        return new CommandResult(result, false);
    }

}
