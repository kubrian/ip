package luna.ui;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import luna.command.ByeCommand;
import luna.command.DeadlineCommand;
import luna.command.DeleteCommand;
import luna.command.EventCommand;
import luna.command.HelpCommand;
import luna.command.ListCommand;
import luna.command.MarkCommand;
import luna.command.TodoCommand;
import luna.command.UnmarkCommand;

public class ParserTest {

    @Test
    void testParseInput_todo() {
        assertInstanceOf(TodoCommand.class, Parser.parseInput("todo eat"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInput("todo"));
    }

    @Test
    void testParseInput_deadline() {
        assertInstanceOf(DeadlineCommand.class, Parser.parseInput("deadline sleep /by 2025/1/1"));
        assertInstanceOf(DeadlineCommand.class,
                Parser.parseInput("deadline sleep /by 2025/1/1 1 PM"));
        assertInstanceOf(DeadlineCommand.class,
                Parser.parseInput("deadline sleep /by 2025/1/1 1:00 PM"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInput("deadline"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInput("deadline sleep"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInput("deadline sleep /by"));
    }

    @Test
    void testParseInput_event() {
        assertInstanceOf(EventCommand.class,
                Parser.parseInput("event cry /from 2025/1/1 /to 2025/1/2"));
        assertInstanceOf(EventCommand.class,
                Parser.parseInput("event cry /from 2025/1/1 1 PM /to 2025/1/2 12:00 AM"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInput("event"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInput("event cry"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInput("event cry /from/to"));
    }

    @Test
    void testParseInput_single() {
        assertInstanceOf(ListCommand.class, Parser.parseInput("list"));
        assertInstanceOf(ByeCommand.class, Parser.parseInput("bye"));
        assertInstanceOf(HelpCommand.class, Parser.parseInput("help"));

        assertThrows(IllegalArgumentException.class, () -> Parser.parseInput("list nonsense"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInput("bye nonsense"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInput("help nonsense"));
    }

    @Test
    void testParseInput_delete() {
        assertInstanceOf(DeleteCommand.class, Parser.parseInput("delete 1"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInput("delete"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInput("delete 1 2"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInput("delete abc"));
    }

    @Test
    void testParseInput_mark() {
        assertInstanceOf(MarkCommand.class, Parser.parseInput("mark 1"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInput("mark"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInput("mark 1 2"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInput("mark abc"));
    }

    @Test
    void testParseInput_unmark() {
        assertInstanceOf(UnmarkCommand.class, Parser.parseInput("unmark 1"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInput("unmark"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInput("unmark 1 2"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInput("unmark abc"));
    }

}
