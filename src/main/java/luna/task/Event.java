package luna.task;

import java.time.LocalDateTime;

/**
 * Represents a task that is an event.
 */
public class Event extends Task {

    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Creates a new event task.
     *
     * @param description The description of this event
     * @param from        The start time of this event
     * @param to          The end time of this event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getCommandString() {
        return String.format("event %s /from %s /to %s", getDescription(),
                from.format(INPUT_DATE_TIME_FORMATTER), to.format(INPUT_DATE_TIME_FORMATTER));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                from.format(DISPLAY_DATE_TIME_FORMATTER), to.format(DISPLAY_DATE_TIME_FORMATTER));
    }

}
