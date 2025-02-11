package luna.task;

/**
 * Represents a task that needs to be done
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStorageString() {
        return String.format("T | %s | %s",
                isCompleted() ? 1 : 0,
                getDescription());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
