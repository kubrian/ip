package luna.command;

/**
 * Supported operations
 */
public enum Operation {
    BYE, HELP, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT;

    /**
     * The help string displaying available commands and their syntax.
     */
    public static final String helpString = """
            Commands:
            - bye
            - help
            - list
            - mark <number>
            - unmark <number>
            - delete <number>
            - todo <description>
            - deadline <description> /by <datetime>
            - event <description> /from <datetime> /to <datetime>
            
            Datetime format: YYYY/M/D[ h[:mm] a]
            The following are all accepted
            2020/12/31 12:00 pm
            2020/12/31 12 pm
            2020/12/31""";
}