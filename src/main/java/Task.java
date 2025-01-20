/**
 * Represents a single task.
 */
public abstract class Task {
    private final String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        markAsNotCompleted();
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    public void markAsNotCompleted() {
        this.completed = false;
    }

    /**
     * Returns the task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the task is completed.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Returns the command string needed to create the task.
     */
    public abstract String getCommandString();

    @Override
    public String toString() {
        return "[" + (completed ? "x" : " ") + "] " + description;
    }
}
