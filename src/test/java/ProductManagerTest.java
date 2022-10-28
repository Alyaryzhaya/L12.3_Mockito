import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.domain.PurchaseItem;
import ru.netology.repository.ProductManager;
import ru.netology.repository.ProductRepository;

import static org.mockito.Mockito.*;

public class ProductManagerTest {

    // создаем репозиторий для всех тестов
  //  ProductRepository repo = new ProductRepository();

    // при работе с Mockito создаем не сам репозиторий а заглушку к репозиторию (в скобках - название класса)
    ProductRepository repo = Mockito.mock(ProductRepository.class);


    // создаем менеджера (обязательно в скобках указать репозиторий, тк менеджер имеет конструктор с параметрами)
    ProductManager manager = new ProductManager(repo);






    // добавим элементы (поля, которые хранят объекты позиций корзины) из нашего класса репозитория  (конструктор)

    PurchaseItem item1 = new PurchaseItem(11, 1, "хлеб", 40, 3); // переисляем все данные, которые нужны конструктору
    PurchaseItem item2 = new PurchaseItem(222, 22, "булка", 30, 1); // переисляем все данные, которые нужны конструктору
    PurchaseItem item3 = new PurchaseItem(3, 30, "картошка", 20, 7); // переисляем все данные, которые нужны конструктору

    // если мы тут попытаемся написать команду, например
    //manager.add(item1) ;
    // она не сработает. Эти команды надо писать именно в методах. Есть аннотации JUnit, которые
    // можно приказать выполнять перед каждым тестом

    // МЕТОД SETUP (подготовка)
    // (удаляем при работе с Mockito)
//    @BeforeEach
//    public void setup() {
//        manager.add(item1);
//        manager.add(item2);
//        manager.add(item3);
//    }


    @Test
    public void shouldReverseAllItems() {

        PurchaseItem[] expected = {item3, item2, item1}; // ожидаем возврат элементов массива в обратном порядке
        PurchaseItem[] actual = manager.getItems();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldSum() {

        // при работе с Mockito (уим заглушку отвечать правильно на вопрос)
        PurchaseItem[] items = {item1, item2, item3};
        // учим заглушку отвечать на вопрос, какие ты запомнила элементы
        doReturn(items ).when(repo).getItems();


        int expected = 290;
        int actual = manager.getTotal();

        Assertions.assertEquals(expected, actual);


    }
}
