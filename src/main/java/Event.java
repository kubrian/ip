import java.time.LocalDateTime;

public class Event extends Task {

    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * @inheritDoc
     */
    public String getCommandString() {
        return String.format("event %s /from %s /to %s", getDescription(),
                from.format(inputDateTimeFormatter), to.format(inputDateTimeFormatter));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                from.format(displayDateTimeFormatter), to.format(displayDateTimeFormatter));
    }

}
