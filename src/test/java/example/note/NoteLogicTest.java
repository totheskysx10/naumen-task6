package example.note;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тесты логики работы с заметками
 */
public class NoteLogicTest {

    /**
     * Логика работы с заметками
     */
    private NoteLogic noteLogic;

    /**
     * Создаёт новый объект NoteLogic перед каждым тестом
     */
    @BeforeEach
    void setup() {
        noteLogic = new NoteLogic();
    }

    /**
     * Тест команд /add и /notes
     */
    @Test
    void testAddAndNotesCommands() {
        String response = noteLogic.handleMessage("/add test note");
        Assertions.assertEquals("Note added!", response);
        Assertions.assertEquals("Your notes:\n" +
                "1. test note", noteLogic.handleMessage("/notes"));
    }

    /**
     * Тест команды /edit
     */
    @Test
    void testEditCommand() {
        noteLogic.handleMessage("/add test note");
        String response = noteLogic.handleMessage("/edit 1 edited note");
        Assertions.assertEquals("Note edited!", response);
        Assertions.assertEquals("Your notes:\n" +
                "1. edited note", noteLogic.handleMessage("/notes"));
    }

    /**
     * Тест команды /del
     */
    @Test
    void testDelCommand() {
        noteLogic.handleMessage("/add first note");
        noteLogic.handleMessage("/add second note");
        String response = noteLogic.handleMessage("/del 1");
        Assertions.assertEquals("Note deleted!", response);
        Assertions.assertEquals("Your notes:\n" +
                "1. second note", noteLogic.handleMessage("/notes"));
    }
}
