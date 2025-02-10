package luna.ui;

import static java.time.temporal.ChronoField.AMPM_OF_DAY;
import static java.time.temporal.ChronoField.CLOCK_HOUR_OF_AMPM;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

import luna.command.ByeCommand;
import luna.command.Command;
import luna.command.DeadlineCommand;
import luna.command.DeleteCommand;
import luna.command.EventCommand;
import luna.command.FindCommand;
import luna.command.HelpCommand;
import luna.command.ListCommand;
import luna.command.MarkCommand;
import luna.command.Operation;
import luna.command.TodoCommand;
import luna.command.UnmarkCommand;

/**
 * A class that parses user input into commands.
 */
public class Parser {

    private static final DateTimeFormatter inputDateTimeFormatter =
            new DateTimeFormatterBuilder().appendPattern("yyyy/M/d[ h[:mm] a]")
                                          .parseDefaulting(CLOCK_HOUR_OF_AMPM, 0)
                                          .parseDefaulting(MINUTE_OF_HOUR, 0)
                                          .parseDefaulting(AMPM_OF_DAY, 0)
                                          .toFormatter();

    private Parser() {
        throw new RuntimeException("Parser is not instantiable");
    }

    /**
     * Returns the command corresponding to the given input string
     *
     * @throws IllegalArgumentException Thrown if the input is invalid
     */
    public static Command parseInput(String input) throws IllegalArgumentException {
        String[] words = input.split(" ", 2);

        // Ensure requested operation is valid
        Operation op;
        try {
            op = Operation.valueOf(words[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Unsupported command: %s", words[0]));
        }

        // Extract arguments
        String args = words.length > 1 ? words[1] : "";

        // Further processing
        // CHECKSTYLE.OFF: Indentation
        return switch (op) {
            case BYE -> parseByeCommand(args);
            case HELP -> parseHelpCommand(args);
            case LIST -> parseListCommand(args);
            case MARK -> parseMarkCommand(args);
            case UNMARK -> parseUnMarkCommand(args);
            case DELETE -> parseDeleteCommand(args);
            case FIND -> parseFindCommand(args);
            case TODO -> parseTodoCommand(args);
            case DEADLINE -> parseDeadlineCommand(args);
            case EVENT -> parseEventCommand(args);
        };
        // CHECKSTYLE.ON: Indentation
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

    private static FindCommand parseFindCommand(String args) throws IllegalArgumentException {
        if (args.isEmpty()) {
            throw new IllegalArgumentException("'find' expects one argument.");
        }
        return new FindCommand(args);
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
            LocalDateTime by = LocalDateTime.parse(comp[1], inputDateTimeFormatter);
            return new DeadlineCommand(comp[0], by);
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
            LocalDateTime from = LocalDateTime.parse(comp[1], inputDateTimeFormatter);
            LocalDateTime to = LocalDateTime.parse(comp[2], inputDateTimeFormatter);
            return new EventCommand(comp[0], from, to);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid datetime format");
        }
    }

}
