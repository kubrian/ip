package luna;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import luna.command.Command;
import luna.storage.Storage;
import luna.task.Task;
import luna.ui.ConsoleUI;
import luna.ui.Parser;

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
    private final ConsoleUI consoleUi;
    private final Storage storage;

    // Data
    private final ArrayList<Task> taskList;

    public Luna(String saveFileName) {
        this.consoleUi = new ConsoleUI(BYE);
        this.taskList = new ArrayList<>();
        this.storage = new Storage(saveFileName);
    }

    public static void main(String[] args) {
        Luna bot = new Luna(saveFileName);
        bot.run();
    }

    public void run() {
        storage.loadTasksFromFile(consoleUi, taskList);
        consoleUi.printOutput(GREETING);
        interactWithUser();
        consoleUi.close();
    }

    /**
     * Interacts with the user and returns whether the user wants to continue.
     * <p>
     * Returns false if the user says bye, true otherwise.
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
                storage.saveTasksToFile(taskList);
                if (!cont) {
                    break;
                }
            } catch (FileNotFoundException e) {
                consoleUi.printOutput("Failed to save tasks to file");
            } catch (IndexOutOfBoundsException e) {
                consoleUi.printOutput("Invalid task number");
            }
        }
    }

}