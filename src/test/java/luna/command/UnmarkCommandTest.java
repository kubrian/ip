package luna.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import luna.storage.Storage;
import luna.task.Task;

public class UnmarkCommandTest {

    @Mock
    private Storage storage;
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
        new UnmarkCommand(1).execute(storage, taskList);
        new UnmarkCommand(2).execute(storage, taskList);
        verify(task, times(2)).markAsNotCompleted();
        assertThrows(IndexOutOfBoundsException.class, () -> new UnmarkCommand(3).execute(null,
                taskList));
    }

}
