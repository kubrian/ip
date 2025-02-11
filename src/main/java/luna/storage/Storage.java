package luna.storage;

import static luna.ui.Parser.INPUT_DATE_TIME_FORMATTER;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import luna.task.Deadline;
import luna.task.Event;
import luna.task.Task;
import luna.task.Todo;

/**
 * Stores and loads tasks from/to a file.
 */
public class Storage {

    private final File saveFile;

    /**
     * Creates a new Storage object.
     *
     * @param saveFileName The path to the save file.
     */
    public Storage(String saveFileName) {
        this.saveFile = new File(saveFileName);
    }

    /**
     * Loads tasks into the given task list from the save file and returns if the loading was
     * successful.
     *
     * @param taskList The ArrayList to load the tasks into.
     */
    public boolean loadTasksFromFile(ArrayList<Task> taskList) {
        assert taskList != null;
        try {
            boolean isFullyLoaded = true;
            BufferedReader br = new BufferedReader(new FileReader(saveFile));
            String line;

            while ((line = br.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    taskList.add(task);
                } else {
                    isFullyLoaded = false;
                }
            }
            br.close();
            return isFullyLoaded;
        } catch (IOException e) {
            return false;
        }
    }

    private Task parseTask(String line) {
        String[] comp = line.split(" \\| ", 3);
        // Ensure at least type | completion | details
        if (comp.length != 3) {
            return null;
        }

        // Result
        Task task;
        String[] args;

        // By type
        switch (comp[0]) {
        case "T":
            // Ensure non empty description
            if (comp[2].isEmpty()) {
                return null;
            }
            task = new Todo(comp[2]);
            break;
        case "D":
            args = comp[2].split(" | ", 2);
            // Ensure by | description
            if (args.length != 2 || args[0].isEmpty() || args[1].isEmpty()) {
                return null;
            }
            try {
                LocalDateTime by = LocalDateTime.parse(args[0], INPUT_DATE_TIME_FORMATTER);
                task = new Deadline(args[1], by);
            } catch (DateTimeParseException e) {
                return null;
            }
            break;
        case "E":
            args = comp[2].split(" | ", 3);
            // Ensure from | to | description
            if (args.length != 3 || args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty()) {
                return null;
            }
            try {
                LocalDateTime from = LocalDateTime.parse(args[0], INPUT_DATE_TIME_FORMATTER);
                LocalDateTime to = LocalDateTime.parse(args[1], INPUT_DATE_TIME_FORMATTER);
                task = new Event(args[2], from, to);
            } catch (DateTimeParseException e) {
                return null;
            }
            break;
        default:
            // Invalid type
            return null;
        }

        // Ensure valid completion
        boolean isCompleted;
        if (comp[1].equals("1")) {
            isCompleted = true;
        } else if (comp[1].equals("0")) {
            isCompleted = false;
        } else {
            return null;
        }

        assert task != null;

        // Mark task as completed
        if (isCompleted) {
            task.markAsCompleted();
        }
        return task;
    }

    /**
     * Saves the given task list to the save file.
     *
     * @return A boolean indicating whether the save was successful.
     */
    public boolean saveTasksToFile(ArrayList<Task> taskList) {
        // Ensure directory exists
        File dir = saveFile.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Write to file
        try {
            PrintWriter pw = new PrintWriter(saveFile);
            assert taskList != null;
            taskList.stream()
                    .map(task -> task.getStorageString())
                    .forEach(pw::println);
            pw.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

}
