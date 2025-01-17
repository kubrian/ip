import java.util.Scanner;

public class Luna {
    public static final String NAME = "Luna";
    public static final String GREETING = "Hello! I'm " + NAME + "!\nWhat can I do for you?\n";
    public static final String BYE = "Bye. Hope to see you again soon!\n";
    public static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        // Greet the user, interact until the user says bye
        System.out.println(GREETING);
        interact();
        System.out.println(BYE);
        scanner.close();
    }

    /**
     * Formats the chatbot response such that it can be printed to the console.
     * <p>
     * Appends the name of the program to the beginning of the response
     *
     * @param response The response from the chatbot
     */
    public static String formatResponse(String response) {
        return String.format("%s: %s\n", NAME, response);
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
            }

            System.out.println(formatResponse(input));
        }
    }
}
