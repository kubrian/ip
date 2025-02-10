package luna;

import java.util.ArrayList;

import luna.command.Command;
import luna.command.CommandResult;
import luna.storage.Storage;
import luna.task.Task;
import luna.ui.ConsoleUi;
import luna.ui.Parser;

/**
 * Represents a chatbot.
 */
public class Luna {

    // Common messages
    public static final String NAME = "Luna";
    public static final String GREETING = String.format("Hello! I'm %s!\nWhat can I do for you?",
            NAME);
    public static final String BYE = "Bye. Hope to see you again soon!";
    public static final String HELP = "'help' to list commands and syntax";

    // Storage
    private static final String saveFileName = "./data/_" + NAME.toLowerCase();

    // I/O
    private final ConsoleUi consoleUi;
    private final Storage storage;

    // Data
    private final ArrayList<Task> taskList;

    /**
     * Constructs a new chatbot instance.
     *
     * @param saveFileName The name of the file to use for storing tasks.
     */
    public Luna(String saveFileName) {
        this.consoleUi = new ConsoleUi();
        this.taskList = new ArrayList<>();
        this.storage = new Storage(saveFileName);
    }

    public static void main(String[] args) {
        Luna bot = new Luna(saveFileName);
        bot.run();
    }

    /**
     * Runs the application.
     */
    public void run() {
        if (!storage.loadTasksFromFile(taskList)) {
            consoleUi.printOutput("Unable to load tasks from file");
        } else {
            consoleUi.printOutput("Loaded " + taskList.size() + " tasks from file");
        }
        consoleUi.printOutput(GREETING);
        String input;
        CommandResult result;
        do {
            input = consoleUi.getInput();
            result = getResponse(input);
            consoleUi.printOutput(result.getOutput());
        } while (!result.isExit());
        close();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public CommandResult getResponse(String input) {
        Command command;
        try {
            command = Parser.parseInput(input);
        } catch (IllegalArgumentException e) {
            return new CommandResult(e.getMessage() + "\n" + HELP, false);
        }

        try {
            return command.execute(storage, taskList);
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("Invalid task number", false);
        }
    }

    public void close() {
        consoleUi.close();
    }

}
