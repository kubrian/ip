/**
 * Represents a task that needs to be done
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * @inheritDoc
     */
    public String getCommandString() {
        return "todo " + getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}