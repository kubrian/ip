package luna.command;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import luna.storage.Storage;
import luna.task.Deadline;
import luna.task.Task;
import luna.ui.ConsoleUi;

public class DeadlineCommandTest {

    @Mock
    private ConsoleUi ui;
    @Mock
    private Storage storage;
    @Mock
    private ArrayList<Task> taskList;
    @Mock
    private LocalDateTime by;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute() {
        new DeadlineCommand("Test", by).execute(ui, storage, taskList);
        verify(taskList).add(any(Deadline.class));
    }

}
