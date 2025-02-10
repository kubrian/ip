package luna.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

public class HelpCommandTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes the mocks
    }

    @Test
    public void testExecute() {
        CommandResult result = new HelpCommand().execute(null, null);
        assertEquals(Operation.HELP_STRING, result.getOutput());
    }

}
