package luna.command;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import luna.ui.ConsoleUi;

public class ByeCommandTest {

    @Mock
    private ConsoleUi ui;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        new ByeCommand().execute(ui, null, null);
        verify(ui).goodbye();
    }

}
