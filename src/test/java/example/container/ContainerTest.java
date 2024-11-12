package example.container;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Тесты для контейнера
 */
public class ContainerTest {

    /**
     * Тест добавления элемента (ещё тест проверки, содержится ли он в контейнере)
     */
    @Test
    void testAddAndContains() {
        Container container = new Container();
        Item item1 = new Item(1L);
        Item item2 = new Item(2L);

        container.add(item1);
        Assertions.assertTrue(container.contains(item1));
        Assertions.assertEquals(1, container.size());

        container.add(item2);
        Assertions.assertTrue(container.contains(item2));
        Assertions.assertEquals(2, container.size());

    }

    /**
     * Тест удаления элемента
     */
    @Test
    void testRemove() {
        Container container = new Container();
        Item item1 = new Item(1L);
        Item item2 = new Item(2L);

        container.add(item1);
        container.add(item2);

        container.remove(item1);

        Assertions.assertTrue(container.contains(item2));
        Assertions.assertFalse(container.contains(item1));
        Assertions.assertEquals(1, container.size());
    }
}
