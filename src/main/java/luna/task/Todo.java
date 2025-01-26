package luna.task;

/**
 * Represents a task that needs to be done
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String getCommandString() {
        return "todo " + getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}