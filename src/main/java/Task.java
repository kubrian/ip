public class Task {
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

    @Override
    public String toString() {
        return "[" + (completed ? "x" : " ") + "] " + description;
    }
}
