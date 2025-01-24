package luna.task;

import java.time.LocalDateTime;

public class Deadline extends Task {

    private final LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, inputDateTimeFormatter);
    }

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * @inheritDoc
     */
    public String getCommandString() {
        return String.format("deadline %s /by %s", getDescription(),
                by.format(inputDateTimeFormatter));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                by.format(displayDateTimeFormatter));
    }

}
