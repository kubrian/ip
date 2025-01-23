import java.time.format.DateTimeParseException;

public class Parser {

    private Parser() {
        throw new RuntimeException("Parser is not instantiable");
    }

    public static Command parseInput(String input) throws IllegalArgumentException {
        // 3 types of errors -> This simply means the string is invalid -> IllegalArgumentException
        String[] words = input.split(" ", 2);

        // Ensure requested operation is valid
        Operation op;
        try {
            op = Operation.valueOf(words[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Unsupported command: %s", words[0]));
        }

        String args;
        try {
            args = words[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            args = "";
        }

        // Further processing
        return switch (op) {
            case BYE -> parseByeCommand(args);
            case HELP -> parseHelpCommand(args);
            case LIST -> parseListCommand(args);
            case MARK -> parseMarkCommand(args);
            case UNMARK -> parseUnMarkCommand(args);
            case DELETE -> parseDeleteCommand(args);
            case TODO -> parseTodoCommand(args);
            case DEADLINE -> parseDeadlineCommand(args);
            case EVENT -> parseEventCommand(args);
        };
    }

    private static ByeCommand parseByeCommand(String args) throws IllegalArgumentException {
        if (!args.isEmpty()) {
            throw new IllegalArgumentException("'bye' expects no arguments.");
        }
        return new ByeCommand();
    }

    private static HelpCommand parseHelpCommand(String args) throws IllegalArgumentException {
        if (!args.isEmpty()) {
            throw new IllegalArgumentException("'help' expects no arguments.");
        }
        return new HelpCommand();
    }

    private static ListCommand parseListCommand(String args) throws IllegalArgumentException {
        if (!args.isEmpty()) {
            throw new IllegalArgumentException("'list' expects no arguments.");
        }
        return new ListCommand();
    }

    private static MarkCommand parseMarkCommand(String args) throws IllegalArgumentException {
        try {
            return new MarkCommand(Integer.parseInt(args));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid task number for 'mark'.");
        }
    }

    private static UnmarkCommand parseUnMarkCommand(String args) throws IllegalArgumentException {
        try {
            return new UnmarkCommand(Integer.parseInt(args));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid task number for 'unmark'.");
        }
    }

    private static DeleteCommand parseDeleteCommand(String args) throws IllegalArgumentException {
        try {
            return new DeleteCommand(Integer.parseInt(args));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("'delete' expects one number as argument.");
        }
    }

    private static TodoCommand parseTodoCommand(String args) throws IllegalArgumentException {
        if (args.isEmpty()) {
            throw new IllegalArgumentException("'todo' expects one argument.");
        }
        return new TodoCommand(args);
    }

    private static DeadlineCommand parseDeadlineCommand(String args) throws IllegalArgumentException {
        String[] comp = args.split(" /by ", 2);
        if (comp.length != 2 || comp[0].isEmpty() || comp[1].isEmpty()) {
            throw new IllegalArgumentException("'deadline' expects description and deadline as "
                    + "arguments.");
        }
        try {
            return new DeadlineCommand(comp[0], comp[1]);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid datetime format");
        }
    }

    private static EventCommand parseEventCommand(String args) throws IllegalArgumentException {
        String[] comp = args.split(" /(from|to) ", 3);
        if (comp.length != 3 || comp[0].isEmpty() || comp[1].isEmpty() || comp[2].isEmpty()) {
            throw new IllegalArgumentException("'event' expects description, from and to as "
                    + "arguments.");
        }
        try {
            return new EventCommand(comp[0], comp[1], comp[2]);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid datetime format");
        }
    }

}
