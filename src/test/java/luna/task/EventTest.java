package luna.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testGetStorageString() {
        LocalDateTime d1 = LocalDateTime.of(2025, 2, 1, 0, 0);
        LocalDateTime d2 = LocalDateTime.of(2025, 11, 20, 15, 5);
        Event e1 = new Event("name1", d1, d2);
        assertEquals("E | 0 | 2025/2/1 12:00 am | 2025/11/20 3:05 pm | name1",
                e1.getStorageString());

        Event e2 = new Event("long name with spaces", d1, d2);
        assertEquals("E | 0 | 2025/2/1 12:00 am | 2025/11/20 3:05 pm | long name with spaces",
                e2.getStorageString());
    }

    @Test
    public void testToString() {
        LocalDateTime d1 = LocalDateTime.of(2025, 2, 1, 0, 0);
        LocalDateTime d2 = LocalDateTime.of(2025, 11, 20, 15, 5);
        Event e1 = new Event("name1", d1, d2);
        assertEquals("[E][ ] name1 (from: 1 Feb 25 12:00 am to: 20 Nov 25 3:05 pm)", e1.toString());
    }

    @Test
    public void testMarkAsCompleted() {
        LocalDateTime d1 = LocalDateTime.of(2025, 2, 1, 0, 0);
        LocalDateTime d2 = LocalDateTime.of(2025, 11, 20, 15, 5);
        Event e1 = new Event("name1", d1, d2);
        e1.markAsCompleted();
        assertEquals("[E][x] name1 (from: 1 Feb 25 12:00 am to: 20 Nov 25 3:05 pm)", e1.toString());
        assertTrue(e1.isCompleted());
    }

    @Test
    public void testMarkAsNotCompleted() {
        LocalDateTime d1 = LocalDateTime.of(2025, 2, 1, 0, 0);
        LocalDateTime d2 = LocalDateTime.of(2025, 11, 20, 15, 5);
        Event e1 = new Event("name1", d1, d2);
        e1.markAsCompleted();
        e1.markAsNotCompleted();
        assertEquals("[E][ ] name1 (from: 1 Feb 25 12:00 am to: 20 Nov 25 3:05 pm)", e1.toString());
        assertFalse(e1.isCompleted());
    }

}
