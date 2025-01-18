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
            String words[] = input.split(" ");

            // Check if the user wants to exit
            switch (words[0]) {
            case "bye":
                return;
            case "list":
                System.out.println(formatList());
                break;
            case "mark":
                int taskNumber = Integer.parseInt(words[1]);
                taskList.get(taskNumber - 1)
                        .markAsCompleted();
                System.out.println("Marked task " + taskNumber + " as completed");
                break;
            case "unmark":
                taskNumber = Integer.parseInt(words[1]);
                taskList.get(taskNumber - 1)
                        .markAsNotCompleted();
                System.out.println("Marked task " + taskNumber + " as not completed");
                break;
            default:
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
}