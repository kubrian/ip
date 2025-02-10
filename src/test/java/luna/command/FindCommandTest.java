package luna.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import luna.task.Task;

public class FindCommandTest {

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

        CommandResult result = new FindCommand("c").execute(null, taskList);
        assertEquals("2: another text", result.getOutput());
    }

}
