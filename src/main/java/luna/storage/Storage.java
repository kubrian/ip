package luna.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import luna.command.ListCommand;
import luna.command.MarkCommand;
import luna.task.Task;
import luna.ui.Parser;

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
        try {
            BufferedReader br = new BufferedReader(new FileReader(saveFile));
            String line;

            while ((line = br.readLine()) != null) {
                String[] comp = line.split(" ", 2);
                // Task type
                Parser.parseInput(comp[1])
                      .execute(this, taskList);

                // Mark last task as completed
                if (comp[0].equals("1")) {
                    new MarkCommand(taskList.size()).execute(this, taskList);
                }
            }
            br.close();
            new ListCommand().execute(this, taskList);
            return true;
        } catch (IOException e) {
            return false;
        }
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
            taskList.stream()
                    .map(task -> (task.isCompleted() ? 1 : 0) + " " + task.getCommandString())
                    .forEach(pw::println);
            pw.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

}
