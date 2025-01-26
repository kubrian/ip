package luna.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import luna.storage.Storage;
import luna.task.Task;
import luna.ui.ConsoleUi;

public class DeleteCommandTest {

    @Mock
    private ConsoleUi ui;

    @Mock
    private Storage storage;

    @Mock
    private Task t1;

    @Mock
    private Task t2;

    private ArrayList<Task> taskList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskList = new ArrayList<>();
        taskList.add(t1);
        taskList.add(t2);
    }

    @Test
    void testExecute() {
        new DeleteCommand(1).execute(ui, storage, taskList);
        assertEquals(t2, taskList.get(0));
        assertEquals(1, taskList.size());
        assertThrows(IndexOutOfBoundsException.class, () ->
                new DeleteCommand(2).execute(ui, null
                        , taskList));
    }

}
