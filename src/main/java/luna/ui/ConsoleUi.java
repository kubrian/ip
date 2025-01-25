package luna.ui;

import java.util.Scanner;

/**
 * A simple text-based user interface.
 */
public class ConsoleUi {

    private final Scanner scanner;
    private final String BYE;

    public ConsoleUi(String bye) {
        BYE = bye;
        scanner = new Scanner(System.in);
    }

    public void close() {
        scanner.close();
    }

    public String getInput() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    public void goodbye() {
        printOutput(BYE);
    }

    public void printOutput(String output) {
        System.out.println(output);
    }

}