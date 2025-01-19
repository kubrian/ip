public enum Command {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, HELP;

    public static String helpString() {
        return """
                Commands:
                bye
                list
                mark <number>
                unmark <number>
                todo <description>
                deadline <description> /by <date>
                event <description> /from <date> /to <date>
                delete <number>
                help""";
    }
}
