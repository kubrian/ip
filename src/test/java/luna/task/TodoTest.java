package luna.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testGetCommandString() {
        Todo t1 = new Todo("name1");
        assertEquals("todo name1", t1.getCommandString());

        Todo t2 = new Todo("long name with spaces");
        assertEquals("todo long name with spaces", t2.getCommandString());
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
    }

    @Test
    public void testMarkAsNotCompleted() {
        Todo t1 = new Todo("name1");
        t1.markAsCompleted();
        t1.markAsNotCompleted();
        assertEquals("[T][ ] name1", t1.toString());
    }

}
