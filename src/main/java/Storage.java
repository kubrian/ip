import java.io.IOException;
import java.util.ArrayList;

/**
 *
 */
public class Storage {
    private final String saveFileName;

    public Storage(String saveFileName) {
        this.saveFileName = saveFileName;
    }

    public ArrayList<Task> loadTasksFromFile() throws IOException {
        return new ArrayList<>();
    }

    public void saveTasksToFile(ArrayList<Task> tasks) {
    }
}
