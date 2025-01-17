import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Luna {
    // Common strings
    public static final String NAME = "Luna";
    public static final String GREETING = "Hello! I'm " + NAME + "!\nWhat can I do for you?";
    public static final String BYE = "Bye. Hope to see you again soon!";

    // I/O
    public static final Scanner scanner = new Scanner(System.in);

    // Data
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        // Greet the user, interact until the user says bye
        System.out.println(GREETING);
        interact();
        System.out.println(BYE);
        scanner.close();
    }


    /**
     * Reads input from the user and prints out a response until the user
     * enters "bye".
     * <p>
     * The response is simply the input given by the user.
     */
    public static void interact() {
        while (true) {
            // Read input
            System.out.print("> ");
            String input = scanner.nextLine();

            // Check if the user wants to exit
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                // Print the current list
                System.out.println(formatList());
            } else if (input.startsWith("mark")) {
                // Mark a task as completed
                try {
                    int index = getIndex(input);
                    taskList.get(index - 1)
                            .markAsCompleted();
                    System.out.println("Marked task " + index + " as completed");
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Invalid index");
                }
            } else if (input.startsWith("unmark")) {
                // Mark a task as completed
                try {
                    int index = getIndex(input);
                    taskList.get(index - 1)
                            .markAsNotCompleted();
                    System.out.println("Marked task " + index + " as not completed");
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Invalid index");
                }
            } else {
                taskList.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
    }

    /**
     * Format the current list of items as a string.
     * <p>
     * Each item is numbered and the string is newline-separated.
     * <p>
     * Example output:
     * <pre>
     * 1: item1
     * 2: item2
     * </pre>
     */
    public static String formatList() {
        return IntStream.range(0, taskList.size())
                        .mapToObj(i -> i + 1 + ": " + taskList.get(i))
                        .collect(Collectors.joining("\n"));
    }

    public static int getIndex(String input) {
        return Integer.parseInt(input.split(" ")[1]);
    }
}