package luna.command;

/**
 * Supported operations
 */
public enum Operation {
    BYE, HELP, LIST, MARK, UNMARK, DELETE, FIND, TODO, DEADLINE, EVENT;

    private static final String COMMAND_STRING = """
            Commands:
            - bye
            - help
            - list
            - mark <number>
            - unmark <number>
            - delete <number>
            - find <keyword>
            - todo <description>
            - deadline <description> /by <datetime>
            - event <description> /from <datetime> /to <datetime>
            """;

    private static final String DATETIME_FORMAT = """
            Datetime format: YYYY/M/D[ h[:mm] a]
            Example formats
            2020/12/31 12:00 PM
            2020/12/31 12 PM
            2020/12/31
            """;

    /**
     * The help string displaying available commands and their syntax.
     */
    public static final String HELP_STRING = COMMAND_STRING + "\n" + DATETIME_FORMAT;
}
