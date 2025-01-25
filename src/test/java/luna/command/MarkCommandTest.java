package luna.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import luna.task.Task;
import luna.ui.ConsoleUi;

public class MarkCommandTest {

    @Mock
    private ConsoleUi ui;

    @Mock
    private Task task;

    private ArrayList<Task> taskList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskList = new ArrayList<>();
        taskList.add(task);
        taskList.add(task);
    }

    @Test
    void testExecute() {
        new MarkCommand(1).execute(ui, null, taskList);
        new MarkCommand(2).execute(ui, null, taskList);
        verify(task, times(2)).markAsCompleted();
        assertThrows(IndexOutOfBoundsException.class, () -> new MarkCommand(3).execute(ui, null,
                taskList));
    }

}
