package luna.command;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import luna.ui.ConsoleUi;

public class HelpCommandTest {

    @Mock
    private ConsoleUi ui;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes the mocks
    }

    @Test
    public void testExecute() {
        new HelpCommand().execute(ui, null, null);
        verify(ui).printOutput(Operation.HELP_STRING);
    }

}
