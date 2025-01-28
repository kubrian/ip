package luna;

import java.util.ArrayList;

import luna.command.Command;
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
        this.consoleUi = new ConsoleUi(BYE);
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
        storage.loadTasksFromFile(consoleUi, taskList);
        consoleUi.printOutput(GREETING);
        interactWithUser();
        consoleUi.close();
    }

    /**
     * Interacts with the user until the user exits the application.
     */
    private void interactWithUser() {
        // Read input
        while (true) {
            String input = consoleUi.getInput();
            Command command;
            try {
                command = Parser.parseInput(input);
            } catch (IllegalArgumentException e) {
                consoleUi.printOutput(e.getMessage());
                consoleUi.printOutput(HELP);
                continue;
            }

            try {
                boolean cont = command.execute(consoleUi, storage, taskList);
                if (!cont) {
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
                consoleUi.printOutput("Invalid task number");
            }
        }
    }

}
