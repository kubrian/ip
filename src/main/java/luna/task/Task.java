package luna.task;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a single task.
 */
public abstract class Task {

    public static final DateTimeFormatter DISPLAY_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("d MMM yy h:mm a", Locale.ENGLISH);

    private final String description;
    private boolean isCompleted;

    /**
     * Creates a new task with the given description.
     */
    protected Task(String description) {
        this.description = description;
        markAsNotCompleted();
    }

    /**
     * Marks this task as not completed.
     */
    public void markAsNotCompleted() {
        this.isCompleted = false;
    }

    /**
     * Marks this task as completed.
     */
    public void markAsCompleted() {
        this.isCompleted = true;
    }

    /**
     * Returns the description of this task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether this task is completed.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Returns the string needed to store this task.
     */
    public abstract String getStorageString();

    @Override
    public String toString() {
        return "[" + (isCompleted ? "x" : " ") + "] " + description;
    }

}
