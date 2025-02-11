package luna.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testGetStorageString() {
        LocalDateTime dt1 = LocalDateTime.of(2025, 2, 1, 0, 0);
        Deadline d1 = new Deadline("name1", dt1);
        assertEquals("D | 0 | 2025/2/1 12:00 am | name1", d1.getStorageString());

        LocalDateTime dt2 = LocalDateTime.of(2025, 11, 20, 15, 5);
        Deadline d2 = new Deadline("long name with spaces", dt2);
        assertEquals("D | 0 | 2025/11/20 3:05 pm | long name with spaces", d2.getStorageString());
    }

    @Test
    public void testToString() {
        LocalDateTime dt1 = LocalDateTime.of(2025, 2, 1, 0, 0);
        Deadline d1 = new Deadline("name1", dt1);
        assertEquals("[D][ ] name1 (by: 1 Feb 25 12:00 am)", d1.toString());

        LocalDateTime dt2 = LocalDateTime.of(2025, 11, 20, 15, 5);
        Deadline d2 = new Deadline("long name with spaces", dt2);
        assertEquals("[D][ ] long name with spaces (by: 20 Nov 25 3:05 pm)",
                d2.toString());
    }

    @Test
    public void testMarkAsCompleted() {
        LocalDateTime dt1 = LocalDateTime.of(2025, 2, 1, 0, 0);
        Deadline d1 = new Deadline("name1", dt1);
        d1.markAsCompleted();
        assertEquals("[D][x] name1 (by: 1 Feb 25 12:00 am)", d1.toString());
        assertTrue(d1.isCompleted());
    }

    @Test
    public void testMarkAsNotCompleted() {
        LocalDateTime dt1 = LocalDateTime.of(2025, 2, 1, 0, 0);
        Deadline d1 = new Deadline("name1", dt1);
        d1.markAsCompleted();
        d1.markAsNotCompleted();
        assertEquals("[D][ ] name1 (by: 1 Feb 25 12:00 am)", d1.toString());
        assertFalse(d1.isCompleted());
    }

}
