package example.bot;

import java.util.ArrayList;
import java.util.List;

/**
 * �������� ��� ��� ������
 */
public class TestBot implements Bot {

    /**
     * ������ ���������, ������� ��� ��������
     */
    List<String> messages = new ArrayList<>();

    @Override
    public void sendMessage(Long chatId, String message) {
        messages.add(message);
    }

    /**
     * @return ������ �� ����� �����������, ������� ��� ��������
     */
    public List<String> getMessages() {
        return messages;
    }
}
