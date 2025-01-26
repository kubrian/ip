package luna.command;

public enum Operation {
    BYE, HELP, LIST, MARK, UNMARK, DELETE, FIND, TODO, DEADLINE, EVENT;

    public static final String helpString = """
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
            
            Datetime format: YYYY/M/D[ h[:mm] a]
            The following are all accepted
            2020/12/31 12:00 pm
            2020/12/31 12 pm
            2020/12/31""";
}
