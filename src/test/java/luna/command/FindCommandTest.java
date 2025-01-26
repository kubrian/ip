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

public class FindCommandTest {

    @Mock
    private ConsoleUi ui;
    @Mock
    private Task t1;
    @Mock
    private Task t2;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute() {
        ArrayList<Task> taskList = new ArrayList<>();
        when(t1.getDescription()).thenReturn("sleep");
        when(t2.getDescription()).thenReturn("cry");
        when(t2.toString()).thenReturn("another text");
        taskList.add(t1);
        taskList.add(t2);

        new FindCommand("c").execute(ui, null, taskList);
        verify(ui).printOutput("2: another text");

    }

}

