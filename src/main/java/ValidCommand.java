public enum ValidCommand {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, HELP;

    public static final String helpString = """
            Commands:
            bye
            list
            mark <number>
            unmark <number>
            todo <description>
            deadline <description> /by <datetime>
            event <description> /from <datetime> /to <datetime>
            delete <number>
            help
            
            Datetime format: YYYY/M/D[ h[:mm] a]
            The following examples are all acceptable
            2020/12/31 12:00 pm
            2020/12/31 12 pm
            2020/12/31""";
}
