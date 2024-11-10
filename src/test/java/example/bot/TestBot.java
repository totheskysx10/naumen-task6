package example.bot;

import java.util.ArrayList;
import java.util.List;

/**
 * Фейковый бот для тестов
 */
public class TestBot implements Bot {

    /**
     * Список сообщений, которые бот отправил
     */
    List<String> messages = new ArrayList<>();

    @Override
    public void sendMessage(Long chatId, String message) {
        messages.add(message);
    }

    /**
     * @return список со всеми сообщениями, которые бот отправил
     */
    public List<String> getMessages() {
        return messages;
    }
}
