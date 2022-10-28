package ru.netology.repository;

import ru.netology.domain.PurchaseItem;
import ru.netology.repository.ProductRepository;

public class ProductManager {

    // позиции в корзине он хранит не самостоятельно, а ерез репозиторий,
    // поэтому заводим яейку:
    private ProductRepository repo;

    // и будем принимать ее ерез конструктор:
    public ProductManager (ProductRepository repo) {
        this.repo = repo;       // репо-поле = репо-параметр




    }
    // создаем метод добавления элементов в корзину
    public void  add (PurchaseItem item) {
                repo.save(item);  // сохранение этого элемента в репозиторий

    }
    //===========================================================================================================================

    // Запрос: покажи мне все позиции в корзине
    public PurchaseItem[] getItems() {       // тип возвращаемого знаения - массив PurchseItem
        PurchaseItem[] all = repo.getItems();       // здесь все элементы в порядке добавления

        // для обратного порядка создаем новый массив, длинной такой же как тот который нам отдал репозиторий
        PurchaseItem[] reversed = new PurchaseItem[all.length];

        // счетчик для реверсированного массива
        for (int i = 0; i < reversed.length; i++) {
            reversed[i] = all[all.length-1-i]; // для яейки 0 нового массива нужна яейка length -1 старого. для 1 - length -1 -1. итд
        }
        return reversed;

        // ТЕСТ пишем
    }
    //======================================================================================================================================



    // Запрос: поситай общую сумму покупки
    public int getTotal () {
        int sum = 0;
        for(PurchaseItem item  :  repo.getItems()) {
            sum = sum + item.getCount() * item.getProductPrice() ;

        }
        return sum;

        // ТЕСТ пишем


    }

}
