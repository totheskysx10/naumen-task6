package example.container;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * ����� ��� ����������
 */
public class ContainerTest {

    /**
     * ���� ���������� �������� (��� ���� ��������, ���������� �� �� � ����������)
     */
    @Test
    void testAddAndContains() {
        Container container = new Container();
        Item item1 = new Item(1L);
        Item item2 = new Item(2L);

        Assertions.assertTrue(container.add(item1));
        Assertions.assertTrue(container.add(item2));

        Assertions.assertEquals(1, container.get(0).getNum());
        Assertions.assertEquals(2, container.get(1).getNum());
        Assertions.assertTrue(container.contains(item1)
                && container.contains(item2));
        Assertions.assertEquals(2, container.size());
    }

    /**
     * ���� �������� ��������
     */
    @Test
    void testRemove() {
        Container container = new Container();
        Item item1 = new Item(1L);
        Item item2 = new Item(2L);

        container.add(item1);
        container.add(item2);

        Assertions.assertTrue(container.remove(item1));

        Assertions.assertTrue(container.contains(item2));
        Assertions.assertFalse(container.contains(item1));
        Assertions.assertEquals(1, container.size());
    }
}
