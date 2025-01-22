import java.util.Scanner;

/**
 * A simple text-based user interface.
 */
public class UI {
    // Common name independent strings
    private static final String BYE = "Bye. Hope to see you again soon!";

    // User I/O
    private static final Scanner scanner = new Scanner(System.in);

    // Common name dependent strings
    private final String GREETING;

    /**
     * Create a new UI with the given name.
     *
     * @param name The name of the chatbot.
     */
    public UI(String name) {
        this.GREETING = String.format("Hello! I'm %s!\nWhat can I do for you?", name);
    }

    /**
     * Close the scanner to end the program.
     */
    public static void close() {
        // Close scanner
        scanner.close();
    }

    /**
     * Get input from the user.
     *
     * @return The input from the user.
     */
    public String getInput() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    /**
     * Print the given output to the user.
     *
     * @param output The output to be printed.
     */
    public void printOutput(String output) {
        System.out.println(output);
    }

    /**
     * Print the greeting message.
     */
    public void greetUser() {
        printOutput(GREETING);
    }

    /**
     * Print the goodbye message.
     */
    public void goodbye() {
        printOutput(BYE);
    }
}