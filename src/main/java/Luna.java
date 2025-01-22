import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Luna {
    // Common strings
    private static final String NAME = "Luna";
    private static final String GREETING = "Hello! I'm " + NAME + "!\nWhat can I do for you?";
    private static final String BYE = "Bye. Hope to see you again soon!";
    private static final String HELP = "'help' to list commands and syntax";
    private static final String UNSUPPORTED = "Unsupported command: " + HELP;
    private static final String INCOMPLETE = "Incomplete command: " + HELP;

    // I/O
    private static final Scanner scanner = new Scanner(System.in);
    private static final String saveFileName = "./data/_" + NAME.toLowerCase();
    private static File saveFile = new File(saveFileName);

    // Data
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        // Greet the user, interact until the user says bye
        System.out.println(GREETING);
        try {
            loadTasksFromFile();
        } catch (IOException e) {
            System.out.println("Unable to load tasks from file.");
        }
        while (interact()) {
        }
    }

    /**
     * Interacts with the user and returns whether the user wants to continue.
     * <p>
     * Returns false if the user says bye, true otherwise.
     */
    private static boolean interact() {
        // Read input
        System.out.print("> ");
        String input = scanner.nextLine();
        String words[] = input.split(" ", 2);

        // Ensure valid command
        Command command;
        try {
            command = Command.valueOf(words[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println(UNSUPPORTED);
            return true;
        }

        // Check simple commands have no further inputs
        if ((command == Command.BYE || command == Command.LIST || command == Command.HELP) && words.length != 1) {
            System.out.println(UNSUPPORTED);
            return true;
        }

        // Simple commands
        switch (command) {
        case BYE:
            System.out.println(BYE);
            return false;
        case LIST:
            printTaskList();
            return true;
        case HELP:
            System.out.println(Command.helpString());
            return true;
        }

        // Check complex commands have arugments
        if (words.length != 2) {
            System.out.println(INCOMPLETE);
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
            System.out.println("Invalid task number");
            System.out.println(HELP);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println(HELP);
        }

        // Task list has changed
        try {
            saveTasksToFile();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to save tasks to file");
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
    private static void printTaskList() {
        IntStream.range(0, taskList.size())
                 .mapToObj(i -> i + 1 + ": " + taskList.get(i))
                 .forEach(System.out::println);
    }

    /**
     * Marks the task specified by the user as completed.
     */
    private static void markAsCompleted(String input) {
        int taskNumber = Integer.parseInt(input);
        taskList.get(taskNumber - 1)
                .markAsCompleted();
        System.out.println("Marked task " + taskNumber + " as completed");
    }

    /**
     * Marks the task specified by the user as not completed.
     */
    private static void markAsNotCompleted(String input) {
        int taskNumber = Integer.parseInt(input);
        taskList.get(taskNumber - 1)
                .markAsNotCompleted();
        System.out.println("Marked task " + taskNumber + " as not completed");
    }

    /**
     * Adds a new todo task.
     *
     * @throws IllegalArgumentException if input is empty
     */
    private static void addToDo(String input) {
        Task task = new ToDo(input);
        taskList.add(task);
        System.out.println("Added new todo:\n" + task);
    }

    /**
     * Adds a new deadline task.
     *
     * @throws IllegalArgumentException if input is empty or invalid
     */
    private static void addDeadline(String input) {
        String[] comp = input.split(" /by ", 2);
        if (comp.length != 2 || comp[0].length() == 0 || comp[1].length() == 0) {
            throw new IllegalArgumentException("Invalid task format");
        }
        try {
            Task task = new Deadline(comp[0], comp[1]);
            taskList.add(task);
            System.out.println("Added new deadline:\n" + task);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid deadline format");
        }
    }

    /**
     * Adds a new event task.
     *
     * @throws IllegalArgumentException if input is empty or invalid
     */
    private static void addEvent(String input) {
        String[] comp = input.split(" /(from|to) ", 3);
        if (comp.length != 3 || comp[0].length() == 0 || comp[1].length() == 0 || comp[2].length() == 0) {
            throw new IllegalArgumentException("Invalid task format");
        }
        try {
            Task task = new Event(comp[0], comp[1], comp[2]);
            taskList.add(task);
            System.out.println("Added new event:\n" + task);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid deadline format");
        }
    }

    /**
     * Deletes the task specified by the user.
     */
    private static void deleteTask(String input) {
        int taskNumber = Integer.parseInt(input);
        Task task = taskList.remove(taskNumber - 1);
        System.out.println("Deleted task " + taskNumber + ":\n" + task);
    }

    /**
     * Saves the current list of tasks to a file.
     * <p>
     * The file format is newline-separated, each line consisting of completion status and task
     * description.
     */
    private static void saveTasksToFile() throws FileNotFoundException {
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

    private static void loadTasksFromFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(saveFile));
        String line;
        System.out.println("Adding tasks from file...");
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
        System.out.println("Loaded tasks! 'list' to view all.");
    }
}