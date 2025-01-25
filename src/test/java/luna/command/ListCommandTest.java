package luna.command;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import luna.task.Task;
import luna.ui.ConsoleUi;

public class ListCommandTest {

    @Mock
    private ConsoleUi ui;

    @Mock
    private Task task;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute() {
        ArrayList<Task> taskList = new ArrayList<>();
        when(task.toString()).thenReturn("name1");
        taskList.add(task);
        taskList.add(task);

        new ListCommand().execute(ui, null, taskList);
        verify(ui).printOutput("1: name1");
        verify(ui).printOutput("2: name1");

    }

}
