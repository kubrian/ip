package luna.command;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import luna.storage.Storage;
import luna.task.Task;
import luna.task.Todo;

public class TodoCommandTest {

    @Mock
    private Storage storage;
    @Mock
    private ArrayList<Task> taskList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute() {
        new TodoCommand("Test").execute(storage, taskList);
        verify(taskList).add(any(Todo.class));
    }

}
