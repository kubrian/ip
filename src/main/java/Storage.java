import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 */
public class Storage {

    private final String saveFileName;
    private final File saveFile;

    public Storage(String saveFileName) {
        this.saveFileName = saveFileName;
        this.saveFile = new File(saveFileName);
    }

    public void loadTasksFromFile(ConsoleUI consoleUi, ArrayList<Task> taskList) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(saveFileName));
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
