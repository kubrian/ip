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
import luna.ui.ConsoleUi;
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
     * Loads tasks from the save file.
     *
     * @param consoleUi The user interface for interacting with the user.
     * @param taskList  The list of tasks to be saved.
     */
    public void loadTasksFromFile(ConsoleUi consoleUi, ArrayList<Task> taskList) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(saveFile));
            String line;
            consoleUi.printOutput("Loading tasks from data file...");

            while ((line = br.readLine()) != null) {
                String[] comp = line.split(" ", 2);
                // Task type
                Parser.parseInput(comp[1])
                      .execute(consoleUi, null, taskList);

                // Mark last task as completed
                if (comp[0].equals("1")) {
                    new MarkCommand(taskList.size()).execute(consoleUi, null, taskList);
                }
            }
            br.close();
            consoleUi.printOutput("");
            consoleUi.printOutput("Loaded " + taskList.size() + " tasks.");
            new ListCommand().execute(consoleUi, null, taskList);
            consoleUi.printOutput("");
        } catch (IOException e) {
            consoleUi.printOutput("Unable to load tasks from file.");
        }
    }

    /**
     * Saves task list to the save file.
     *
     * @throws FileNotFoundException Thrown if the save file cannot be created.
     */
    public void saveTasksToFile(ArrayList<Task> taskList) throws FileNotFoundException {
        // Ensure directory exists
        File dir = saveFile.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Write to file
        PrintWriter pw = new PrintWriter(saveFile);
        taskList.stream()
                .map(task -> (task.isCompleted() ? 1 : 0) + " " + task.getCommandString())
                .forEach(pw::println);
        pw.close();
    }

}
