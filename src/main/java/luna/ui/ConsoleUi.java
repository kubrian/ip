package luna.ui;

import java.util.Scanner;

/**
 * A simple text-based user interface.
 */
public class ConsoleUi {

    private final Scanner scanner;
    private final String BYE;

    /**
     * Creates a new ConsoleUi.
     *
     * @param bye The message to print upon exit.
     */
    public ConsoleUi(String bye) {
        BYE = bye;
        scanner = new Scanner(System.in);
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Prompts the user for input and returns it.
     */
    public String getInput() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    /**
     * Prints the exit message to the user.
     */
    public void goodbye() {
        printOutput(BYE);
    }

    /**
     * Prints the given message to the user.
     */
    public void printOutput(String output) {
        System.out.println(output);
    }

}