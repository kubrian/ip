package luna.task;

import java.time.LocalDateTime;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    private final LocalDateTime by;

    /**
     * Creates a new deadline task.
     *
     * @param description The description of this task
     * @param by          The deadline of this task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getStorageString() {
        return String.format("D | %s | %s | %s",
                isCompleted() ? 1 : 0,
                by.format(DISPLAY_DATE_TIME_FORMATTER),
                getDescription());
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                by.format(DISPLAY_DATE_TIME_FORMATTER));
    }

}
