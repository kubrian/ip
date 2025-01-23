import java.util.Scanner;

/**
 * A simple text-based user interface.
 */
public class ConsoleUI {
    private final Scanner scanner;

    private final String NAME;
    private final String GREETING;
    private final String BYE;

    private final String HELP;
    private final String UNSUPPORTED;
    private final String INCORRECT;

    public ConsoleUI(String name, String greeting, String bye, String help, String unsupported,
                     String incorrectSyntax) {
        // Set strings
        NAME = name;
        GREETING = greeting;
        BYE = bye;
        HELP = help;
        UNSUPPORTED = unsupported;
        INCORRECT = incorrectSyntax;
        scanner = new Scanner(System.in);
    }

    public void close() {
        scanner.close();
    }

    public String getInput() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    public void greetUser() {
        printOutput(GREETING);
    }

    public void printOutput(String output) {
        System.out.println(output);
    }

    public void goodbye() {
        printOutput(BYE);
    }

    public void showHelp() {
        printOutput(HELP);
    }

    public void printUnsupported() {
        printOutput(UNSUPPORTED);
    }

    public void printIncorrectSyntax() {
        printOutput(INCORRECT);
    }
}