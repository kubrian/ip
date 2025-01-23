import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Luna {
    // Common messages
    private static final String NAME = "Luna";
    private static final String GREETING = String.format("Hello! I'm %s!\nWhat can I do for you?"
            , NAME);
    private static final String BYE = "Bye. Hope to see you again soon!";
    private static final String HELP = "'help' to list commands and syntax";
    private static final String UNSUPPORTED = "Unsupported command: " + HELP;
    private static final String INCOMPLETE = "Incomplete command: " + HELP;

    // Storage
    private static final String saveFileName = "./data/_" + NAME.toLowerCase();
    private static File saveFile = new File(saveFileName);

    // I/O
    private ConsoleUI consoleUi;

    // Data
    private ArrayList<Task> taskList;

    public Luna(ConsoleUI consoleUi) {
        this.consoleUi = consoleUi;
        this.taskList = new ArrayList<>();
    }

    public static void main(String[] args) {
        Luna bot = new Luna(new ConsoleUI(Luna.NAME, Luna.GREETING, Luna.BYE));
        bot.loadTasksFromFile();
        bot.greetUser();
        bot.run();
        bot.close();
    }

    private void loadTasksFromFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(saveFile));
            String line;
            consoleUi.printOutput("Adding tasks from file...");
            while ((line = br.readLine()) != null) {
                String[] comp = line.split(" ", 3);

                // Task type
                String type = comp[1];
                String input = comp[2];
                if (type.equals("todo")) {
                    addToDo(input);
                } else if (type.equals("deadline")) {
                    addDeadline(input);
                } else if (type.equals("event")) {
                    addEvent(input);
                }

                // Completion
                boolean completed = Integer.parseInt(comp[0]) != 0;
                if (completed) {
                    taskList.get(taskList.size() - 1)
                            .markAsCompleted();
                }
            }
            br.close();
            consoleUi.printOutput("Loaded tasks! 'list' to view all.");
        } catch (IOException e) {
            consoleUi.printOutput("Unable to load tasks from file.");
            return;
        }
    }

    private void greetUser() {
        consoleUi.greetUser();
    }

    public void run() {
        while (interact()) {
        }
    }

    public void close() {
        ConsoleUI.close();
    }

    /**
     * Adds a new todo task.
     *
     * @throws IllegalArgumentException if input is empty
     */
    private void addToDo(String input) {
        Task task = new ToDo(input);
        taskList.add(task);
        consoleUi.printOutput("Added new todo:\n" + task);
    }

    /**
     * Adds a new deadline task.
     *
     * @throws IllegalArgumentException if input is empty or invalid
     */
    private void addDeadline(String input) {
        String[] comp = input.split(" /by ", 2);
        if (comp.length != 2 || comp[0].length() == 0 || comp[1].length() == 0) {
            throw new IllegalArgumentException("Invalid task format");
        }
        try {
            Task task = new Deadline(comp[0], comp[1]);
            taskList.add(task);
            consoleUi.printOutput("Added new deadline:\n" + task);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid deadline format");
        }
    }

    /**
     * Adds a new event task.
     *
     * @throws IllegalArgumentException if input is empty or invalid
     */
    private void addEvent(String input) {
        String[] comp = input.split(" /(from|to) ", 3);
        if (comp.length != 3 || comp[0].length() == 0 || comp[1].length() == 0 || comp[2].length() == 0) {
            throw new IllegalArgumentException("Invalid task format");
        }
        try {
            Task task = new Event(comp[0], comp[1], comp[2]);
            taskList.add(task);
            consoleUi.printOutput("Added new event:\n" + task);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid deadline format");
        }
    }

    /**
     * Interacts with the user and returns whether the user wants to continue.
     * <p>
     * Returns false if the user says bye, true otherwise.
     */
    private boolean interact() {
        // Read input
        String input = consoleUi.getInput();
        String words[] = input.split(" ", 2);

        // Ensure valid command
        Command command;
        try {
            command = Command.valueOf(words[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            consoleUi.printOutput(UNSUPPORTED);
            return true;
        }

        // Check simple commands have no further inputs
        if ((command == Command.BYE || command == Command.LIST || command == Command.HELP) && words.length != 1) {
            consoleUi.printOutput(UNSUPPORTED);
            return true;
        }

        // Simple commands
        switch (command) {
        case BYE:
            consoleUi.goodbye();
            return false;
        case LIST:
            printTaskList();
            return true;
        case HELP:
            consoleUi.printOutput(Command.helpString());
            return true;
        }

        // Check complex commands have arugments
        if (words.length != 2) {
            consoleUi.printOutput(INCOMPLETE);
            return true;
        }

        // Complex commands
        String taskNumOrDesc = words[1];
        try {
            switch (command) {
            case MARK:
                markAsCompleted(taskNumOrDesc);
                break;
            case UNMARK:
                markAsNotCompleted(taskNumOrDesc);
                break;
            case DELETE:
                deleteTask(taskNumOrDesc);
                break;
            case TODO:
                addToDo(taskNumOrDesc);
                break;
            case DEADLINE:
                addDeadline(taskNumOrDesc);
                break;
            case EVENT:
                addEvent(taskNumOrDesc);
                break;
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            consoleUi.printOutput("Invalid task number");
            consoleUi.printOutput(HELP);
        } catch (IllegalArgumentException e) {
            consoleUi.printOutput(e.getMessage());
            consoleUi.printOutput(HELP);
        }

        // Task list has changed
        try {
            saveTasksToFile();
        } catch (FileNotFoundException e) {
            consoleUi.printOutput("Failed to save tasks to file");
        }
        return true;
    }

    /**
     * Prints the current list of tasks.
     * <p>
     * Each item is numbered and the string is newline-separated.
     * <p>
     * Example output:
     * <pre>
     * 1: item1
     * 2: item2
     * </pre>
     */
    private void printTaskList() {
        IntStream.range(0, taskList.size())
                 .mapToObj(i -> i + 1 + ": " + taskList.get(i))
                 .forEach(consoleUi::printOutput);
    }

    /**
     * Marks the task specified by the user as completed.
     */
    private void markAsCompleted(String input) {
        int taskNumber = Integer.parseInt(input);
        taskList.get(taskNumber - 1)
                .markAsCompleted();
        consoleUi.printOutput("Marked task " + taskNumber + " as completed");
    }

    /**
     * Marks the task specified by the user as not completed.
     */
    private void markAsNotCompleted(String input) {
        int taskNumber = Integer.parseInt(input);
        taskList.get(taskNumber - 1)
                .markAsNotCompleted();
        consoleUi.printOutput("Marked task " + taskNumber + " as not completed");
    }

    /**
     * Deletes the task specified by the user.
     */
    private void deleteTask(String input) {
        int taskNumber = Integer.parseInt(input);
        Task task = taskList.remove(taskNumber - 1);
        consoleUi.printOutput("Deleted task " + taskNumber + ":\n" + task);
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