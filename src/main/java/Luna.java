import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Luna {
    // Common strings
    public static final String NAME = "Luna";
    public static final String GREETING = "Hello! I'm " + NAME + "!\nWhat can I do for you?";
    public static final String BYE = "Bye. Hope to see you again soon!";
    public static final String UNSUPPORTED = "Unsupported command: Type 'help' for a list of " +
            "commands.";
    public static final String INCOMPLETE = "Incomplete command: Type 'help' for a list of " +
            "commands.";

    // I/O
    public static final Scanner scanner = new Scanner(System.in);

    // Data
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        // Greet the user, interact until the user says bye
        System.out.println(GREETING);
        while (interact()) {
        }
    }


    /**
     * Interacts with the user and returns whether the user wants to continue.
     * <p>
     * Returns false if the user says bye, true otherwise.
     */
    public static boolean interact() {
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
            break;
        case HELP:
            System.out.println(Command.helpString());
            break;
        }

        // Check complex commands have arugments
        if (words.length != 2) {
            System.out.println(INCOMPLETE);
            return true;
        }

        switch (command) {
        case MARK:
            try {
                markAsCompleted(words[1]);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Invalid task number: " + words[1]);
            }
            break;
        case UNMARK:
            try {
                markAsNotCompleted(words[1]);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Invalid task number: " + words[1]);
            }
            break;
        case TODO:
            try {
                addToDo(words[1]);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            break;
        case DEADLINE:
            try {
                addDeadline(words[1]);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            break;
        case EVENT:
            try {
                addEvent(words[1]);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            break;
        case DELETE:
            try {
                deleteTask(words[1]);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Invalid task number: " + words[1]);
            }
            break;
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
    public static void printTaskList() {
        IntStream.range(0, taskList.size())
                 .mapToObj(i -> i + 1 + ": " + taskList.get(i))
                 .forEach(System.out::println);
    }

    /**
     * Marks the task specified by the user as completed.
     */
    public static void markAsCompleted(String input) {
        int taskNumber = Integer.parseInt(input);
        taskList.get(taskNumber - 1)
                .markAsCompleted();
        System.out.println("Marked task " + taskNumber + " as completed");
    }

    /**
     * Marks the task specified by the user as not completed.
     */
    public static void markAsNotCompleted(String input) {
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
    public static void addToDo(String input) throws IllegalArgumentException {
        Task task = new ToDo(input);
        taskList.add(task);
        System.out.println("Added new todo:\n" + task);
    }

    /**
     * Adds a new deadline task.
     *
     * @throws IllegalArgumentException if input is empty or invalid
     */
    public static void addDeadline(String input) {
        String[] comp = input.split(" /by ", 2);
        if (comp.length != 2 || comp[0].length() == 0 || comp[1].length() == 0) {
            throw new IllegalArgumentException("Invalid task format");
        }
        Task task = new Deadline(comp[0], comp[1]);
        taskList.add(task);
        System.out.println("Added new deadline:\n" + task);
    }

    /**
     * Adds a new event task.
     *
     * @throws IllegalArgumentException if input is empty or invalid
     */
    public static void addEvent(String input) {
        String[] comp = input.split(" /(from|to) ", 3);
        if (comp.length != 3 || comp[0].length() == 0 || comp[1].length() == 0 || comp[2].length() == 0) {
            throw new IllegalArgumentException("Invalid task format");
        }
        Task task = new Event(comp[0], comp[1], comp[2]);
        taskList.add(task);
        System.out.println("Added new event:\n" + task);
    }

    /**
     * Deletes the task specified by the user.
     */
    public static void deleteTask(String input) {
        int taskNumber = Integer.parseInt(input);
        Task task = taskList.remove(taskNumber - 1);
        System.out.println("Deleted task " + taskNumber + ":\n" + task);
    }

}