import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Luna {

    // Common messages
    public static final String NAME = "Luna";
    public static final String GREETING = String.format("Hello! I'm %s!\nWhat can I do for you?",
            NAME);
    public static final String BYE = "Bye. Hope to see you again soon!";
    public static final String HELP = "'help' to list commands and syntax";
    public static final String UNSUPPORTED = "Unsupported command: " + HELP;
    public static final String INCOMPLETE = "Incomplete command: " + HELP;

    // Storage
    private static final String saveFileName = "./data/_" + NAME.toLowerCase();
    private static final File saveFile = new File(saveFileName);

    // I/O
    private final ConsoleUI consoleUi;

    // Data
    private final ArrayList<Task> taskList;

    public Luna() {
        this.consoleUi = new ConsoleUI(NAME, GREETING, BYE, HELP, UNSUPPORTED, INCOMPLETE);
        this.taskList = new ArrayList<>();
    }

    public static void main(String[] args) {
        Luna bot = new Luna();
        bot.loadTasksFromFile();
        bot.greetUser();
        bot.run();
    }

    private void loadTasksFromFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(saveFile));
            String line;
            consoleUi.printOutput("Loading tasks from data file...");
            while ((line = br.readLine()) != null) {
                String[] comp = line.split(" ", 2);
                // Task type
                Parser.parseInput(comp[1])
                      .execute(consoleUi, null, taskList);
                // Completion
                if (comp[0].equals("1")) {
                    // Mark last task as completed
                    new MarkCommand(taskList.size()).execute(consoleUi, null, taskList);
                }
            }
            br.close();
            consoleUi.printOutput("");
            consoleUi.printOutput("Loaded " + taskList.size() + " tasks.");
            new ListCommand().execute(consoleUi, null, taskList);
            consoleUi.printOutput("");
        } catch (IOException e) {
            consoleUi.printOutput("Unable to load tasks from file.");
        }
    }

    private void greetUser() {
        consoleUi.greetUser();
    }

    public void run() {
        while (interact()) {
        }
        consoleUi.close();
    }

    /**
     * Interacts with the user and returns whether the user wants to continue.
     * <p>
     * Returns false if the user says bye, true otherwise.
     */
    private boolean interact() {
        // Read input
        String input = consoleUi.getInput();
        Command command;
        try {
            command = Parser.parseInput(input);
        } catch (IllegalArgumentException e) {
            consoleUi.printOutput(e.getMessage());
            consoleUi.printOutput(HELP);
            return true;
        }

        try {
            boolean cont = command.execute(consoleUi, null, taskList);
            saveTasksToFile();
            return cont;
        } catch (FileNotFoundException e) {
            consoleUi.printOutput("Failed to save tasks to file");
        } catch (IndexOutOfBoundsException e) {
            consoleUi.printOutput("Invalid task number");
        }

        return true;
    }

    /**
     * Saves the current list of tasks to a file.
     * <p>
     * The file format is newline-separated, each line consisting of completion status and task
     * description.
     */
    private void saveTasksToFile() throws FileNotFoundException {
        // Ensure directory exists
        File dir = saveFile.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // Write to file
        PrintWriter pw = new PrintWriter(saveFile);
        taskList.stream()
                .map(task -> (task.isCompleted() ? 1 : 0) + " " + task.getCommandString())
                .forEach(pw::println);
        pw.close();
    }

}