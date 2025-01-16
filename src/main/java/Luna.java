public class Luna {
    public static final String NAME = "Luna";

    public static void main(String[] args) {
        greet();
    }

    /**
     * Initial greeting
     */
    public static void greet() {
        String header = """
                ____________________________________________________________
                Hello! I'm %s
                What can I do for you?
                ____________________________________________________________
                Bye. Hope to see you again soon!
                ____________________________________________________________""";

        System.out.printf(header, NAME);
    }
}
