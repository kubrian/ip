package luna.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testGetCommandString() {
        ToDo t1 = new ToDo("name1");
        assertEquals("todo name1", t1.getCommandString());

        ToDo t2 = new ToDo("long name with spaces");
        assertEquals("todo long name with spaces", t2.getCommandString());
    }

    @Test
    public void testToString() {
        ToDo t1 = new ToDo("name1");
        assertEquals("[T][ ] name1", t1.toString());

        ToDo t2 = new ToDo("long name with spaces");
        assertEquals("[T][ ] long name with spaces", t2.toString());
    }

    @Test
    public void testMarkAsCompleted() {
        ToDo t1 = new ToDo("name1");
        t1.markAsCompleted();
        assertEquals("[T][x] name1", t1.toString());
    }

    @Test
    public void testMarkAsNotCompleted() {
        ToDo t1 = new ToDo("name1");
        t1.markAsCompleted();
        t1.markAsNotCompleted();
        assertEquals("[T][ ] name1", t1.toString());
    }

}
