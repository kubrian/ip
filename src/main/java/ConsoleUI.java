import java.util.Scanner;

/**
 * A simple text-based user interface.
 */
public class ConsoleUI {
    private static final Scanner scanner = new Scanner(System.in);

    private final String NAME;
    private final String GREETING;
    private final String BYE;

    /**
     * Create a new ConsoleUI with the given name.
     *
     * @param name The name of the chatbot.
     */
    public ConsoleUI(String name, String greeting, String bye) {
        this.NAME = name;
        this.GREETING = greeting;
        this.BYE = bye;
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
     * Print the greeting message.
     */
    public void greetUser() {
        printOutput(GREETING);
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
     * Print the goodbye message.
     */
    public void goodbye() {
        printOutput(BYE);
    }
}