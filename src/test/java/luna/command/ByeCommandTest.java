package luna.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

public class ByeCommandTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        CommandResult result = new ByeCommand().execute(null, null);
        assertTrue(result.isExit());
        assertEquals(ByeCommand.BYE, result.getOutput());
    }

}
