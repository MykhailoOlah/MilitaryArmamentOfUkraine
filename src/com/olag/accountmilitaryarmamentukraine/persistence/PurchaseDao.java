package com.olag.accountmilitaryarmamentukraine.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.olag.accountmilitaryarmamentukraine.domain.JsonService;
import com.olag.accountmilitaryarmamentukraine.persistence.entity.Purchase;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Клас для роботи з базою даних покупок у системі.
 */
public class PurchaseDao {
    private List<Purchase> purchaseList;

    /**
     * Конструктор класу, ініціалізує список покупок з файлу.
     */
    public PurchaseDao() {
        purchaseList = JsonService.jsonPurchaseList();
    }

    /**
     * Отримує покупку за заданим ідентифікатором.
     *
     * @param id Ідентифікатор покупки.
     * @return Об'єкт Optional, що містить покупку, якщо вона існує.
     */
    public Optional<Purchase> get(int id) {
        return Optional.ofNullable(purchaseList.get(id));
    }

    /**
     * Отримує всі покупки з бази даних.
     *
     * @return Список покупок.
     */
    public List<Purchase> getAll() {
        return purchaseList;
    }

    Path path = Path.of("resources", "ListPurchase.json");

    /**
     * Зберігає нову покупку у базі даних.
     *
     * @param purchase Об'єкт покупки для збереження.
     */
    public void savePurchase(Purchase purchase) {
        purchaseList.add(purchase);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String jsonForPurchase = gson.toJson(purchaseList);
        try {
            JsonService.writeString(path, jsonForPurchase);
        } catch (IOException e) {
            System.out.println("Помилка❌ " + e.getMessage());
        }
    }

    /**
     * Оновлює інформацію про покупку у базі даних.
     *
     * @param purchase   Об'єкт покупки для оновлення.
     * @param userChoice Індекс покупки у списку для оновлення.
     */
    public void updatePurchase(Purchase purchase, int userChoice) {
        purchaseList.set(userChoice, purchase);
        Gson gson = new Gson();
        String jsonForPurchase = gson.toJson(purchaseList);
        try {
            JsonService.writeString(path, jsonForPurchase);
        } catch (IOException e) {
            System.out.println("Помилка❌ " + e.getMessage());
        }
    }

    /**
     * Видаляє покупку з бази даних.
     *
     * @param userChoice Індекс покупки у списку для видалення.
     */
    public void deletePurchase(int userChoice) {
        Path path = Path.of("resources", "ListPurchase.json");
        purchaseList.remove(userChoice);
        Gson gson = new Gson();
        String jsonForPurchase = gson.toJson(purchaseList);
        try {
            JsonService.writeString(path, jsonForPurchase);
        } catch (IOException e) {
            System.out.println("Помилка❌ " + e.getMessage());
        }
    }

    /**
     * Отримує список покупок за заданим логіном користувача.
     *
     * @param userName Логін користувача.
     * @return Список покупок за заданим логіном користувача.
     */
    public List<Purchase> getPurchasesByUser(String userName) {
        return purchaseList.stream()
                .filter(purchase -> purchase.getUser() != null && purchase.getUser().equalsIgnoreCase(userName))
                .collect(Collectors.toList());
    }

}
