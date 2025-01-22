import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import static java.time.temporal.ChronoField.AMPM_OF_DAY;
import static java.time.temporal.ChronoField.CLOCK_HOUR_OF_AMPM;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;

/**
 * Represents a single task.
 */
public abstract class Task {
    protected static final DateTimeFormatter inputDateTimeFormatter =
            new DateTimeFormatterBuilder().appendPattern("yyyy/M/d[ h[:mm] a]")
                                          .parseDefaulting(CLOCK_HOUR_OF_AMPM, 0)
                                          .parseDefaulting(MINUTE_OF_HOUR, 0)
                                          .parseDefaulting(AMPM_OF_DAY, 0)
                                          .toFormatter();
    protected static final DateTimeFormatter displayDateTimeFormatter =
            DateTimeFormatter.ofPattern("d MMM yy h:mm a");
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
