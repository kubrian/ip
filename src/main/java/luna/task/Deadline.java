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

    public String getCommandString() {
        return String.format("deadline %s /by %s", getDescription(),
                by.format(INPUT_DATE_TIME_FORMATTER));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                by.format(DISPLAY_DATE_TIME_FORMATTER));
    }

}
