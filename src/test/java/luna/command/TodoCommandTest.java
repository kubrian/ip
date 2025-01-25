package luna.command;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import luna.task.Task;
import luna.task.Todo;
import luna.ui.ConsoleUi;

public class TodoCommandTest {

    @Mock
    private ConsoleUi ui;

    @Mock
    private ArrayList<Task> taskList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute() {
        new TodoCommand("Test").execute(ui, null, taskList);
        verify(taskList).add(any(Todo.class));
    }

}
