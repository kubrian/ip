package luna.task;

import static java.time.temporal.ChronoField.AMPM_OF_DAY;
import static java.time.temporal.ChronoField.CLOCK_HOUR_OF_AMPM;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * Represents a single task.
 */
public abstract class Task {

    protected static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER =
            new DateTimeFormatterBuilder().appendPattern("yyyy/M/d[ h[:mm] a]")
                                          .parseDefaulting(CLOCK_HOUR_OF_AMPM, 0)
                                          .parseDefaulting(MINUTE_OF_HOUR, 0)
                                          .parseDefaulting(AMPM_OF_DAY, 0)
                                          .toFormatter();
    protected static final DateTimeFormatter DISPLAY_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("d MMM yy h:mm a");
    private final String description;
    private boolean completed;

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
        this.completed = false;
    }

    /**
     * Marks this task as completed.
     */
    public void markAsCompleted() {
        this.completed = true;
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
        return completed;
    }

    /**
     * Returns the string needed to store this task.
     */
    public abstract String getStorageString();

    @Override
    public String toString() {
        return "[" + (completed ? "x" : " ") + "] " + description;
    }

}
