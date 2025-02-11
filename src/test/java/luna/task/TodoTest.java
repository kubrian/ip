package luna.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testGetStorageString() {
        Todo t1 = new Todo("name1");
        assertEquals("T | 0 | name1", t1.getStorageString());

        Todo t2 = new Todo("long name with spaces");
        assertEquals("T | 0 | long name with spaces", t2.getStorageString());
    }

    @Test
    public void testToString() {
        Todo t1 = new Todo("name1");
        assertEquals("[T][ ] name1", t1.toString());

        Todo t2 = new Todo("long name with spaces");
        assertEquals("[T][ ] long name with spaces", t2.toString());
    }

    @Test
    public void testMarkAsCompleted() {
        Todo t1 = new Todo("name1");
        t1.markAsCompleted();
        assertEquals("[T][x] name1", t1.toString());
        assertTrue(t1.isCompleted());
    }

    @Test
    public void testMarkAsNotCompleted() {
        Todo t1 = new Todo("name1");
        t1.markAsCompleted();
        t1.markAsNotCompleted();
        assertEquals("[T][ ] name1", t1.toString());
        assertFalse(t1.isCompleted());
    }

}
