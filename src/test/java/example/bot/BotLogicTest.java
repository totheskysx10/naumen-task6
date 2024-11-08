package example.bot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тесты логики бота
 */
public class BotLogicTest {

    /**
     * Фейковый бот
     */
    private TestBot bot;

    /**
     * Логика бота
     */
    private BotLogic botLogic;

    /**
     * Пользователь
     */
    private User user;

    /**
     * Создаёт новые объекты Bot и BotLogic перед каждым тестом
     */
    @BeforeEach
    void setup() {
        bot = new TestBot();
        botLogic = new BotLogic(bot);
        user = new User(1L);
    }

    /**
     * Тест команды /test при корректном ответе на вопрос
     */
    @Test
    void testTestCommandCorrectAnswer() {
        botLogic.processCommand(user, "/test");
        Assertions.assertEquals("Вычислите степень: 10^2", bot.getMessages().get(0));

        botLogic.processCommand(user, "100");
        Assertions.assertEquals("Правильный ответ!", bot.getMessages().get(1));
    }

    /**
     * Тест команды /test при некорректном ответе на вопрос
     */
    @Test
    void testTestCommandIncorrectAnswer() {
        botLogic.processCommand(user, "/test");
        botLogic.processCommand(user, "1");
        Assertions.assertEquals("Вы ошиблись, верный ответ: 100", bot.getMessages().get(1));
        Assertions.assertEquals(1, user.getWrongAnswerQuestions().size());
        Assertions.assertEquals("Вычислите степень: 10^2", user.getWrongAnswerQuestions().get(0).text());
    }

    /**
     * Тест команды /notify при корректно заданном таймере
     */
    @Test
    void testNotifyCommand() throws InterruptedException {
        botLogic.processCommand(user, "/notify");
        Assertions.assertEquals("Введите текст напоминания", bot.getMessages().get(0));
        Assertions.assertEquals(State.SET_NOTIFY_TEXT, user.getState());
        Assertions.assertNotNull(user.getNotification());

        botLogic.processCommand(user, "test");
        Assertions.assertEquals("Через сколько секунд напомнить?", bot.getMessages().get(1));
        Assertions.assertEquals(State.SET_NOTIFY_DELAY, user.getState());

        botLogic.processCommand(user, "1");
        Assertions.assertEquals("Напоминание установлено", bot.getMessages().get(2));
        Assertions.assertEquals(State.INIT, user.getState());

        Assertions.assertEquals(3, bot.getMessages().size());
        Thread.sleep(1015);
        Assertions.assertEquals("Сработало напоминание: 'test'", bot.getMessages().get(3));
    }

    /**
     * Тест команды /notify при некорректно заданном таймере
     */
    @Test
    void testNotifyCommandInvalidTimer() {
        botLogic.processCommand(user, "/notify");
        botLogic.processCommand(user, "test");
        botLogic.processCommand(user, "time");

        Assertions.assertEquals("Пожалуйста, введите целое число", bot.getMessages().get(2));
        botLogic.processCommand(user, "1");
        Assertions.assertEquals("Напоминание установлено", bot.getMessages().get(3));
    }

    /**
     * Тест команды /repeat, если есть вопросы с неверным ответом
     */
    @Test
    void testTestCommandRepeatWithIncorrectQuestions() {
        botLogic.processCommand(user, "/test");
        botLogic.processCommand(user, "100");
        botLogic.processCommand(user, "7");

        botLogic.processCommand(user, "/repeat");
        Assertions.assertEquals(State.REPEAT, user.getState());
        Assertions.assertEquals("Сколько будет 2 + 2 * 2", bot.getMessages().get(5));
        botLogic.processCommand(user, "6");
        Assertions.assertEquals("Тест завершен", bot.getMessages().get(7));
        Assertions.assertEquals(State.INIT, user.getState());
        Assertions.assertTrue(user.getWrongAnswerQuestions().isEmpty());
    }

    /**
     * Тест команды /repeat, если нет вопросов с неверным ответом
     */
    @Test
    void testTestCommandRepeatWithoutIncorrectQuestions() {
        botLogic.processCommand(user, "/test");
        botLogic.processCommand(user, "100");
        botLogic.processCommand(user, "6");

        botLogic.processCommand(user, "/repeat");
        Assertions.assertEquals("Нет вопросов для повторения", bot.getMessages().get(5));
        Assertions.assertTrue(user.getWrongAnswerQuestions().isEmpty());
    }
}
