package com.olag.accountmilitaryarmamentukraine.aui;

import com.olag.accountmilitaryarmamentukraine.persistence.GunsDao;
import com.olag.accountmilitaryarmamentukraine.persistence.PurchaseDao;
import com.olag.accountmilitaryarmamentukraine.persistence.entity.Guns;
import com.olag.accountmilitaryarmamentukraine.persistence.entity.Purchase;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Клас PurchaseFunctional містить методи для взаємодії з функціоналом, пов'язаним з придбанням зброї.
 */
public class PurchaseFunctional {
    private PurchaseDao purchaseDao;

    /**
     * Конструктор класу PurchaseFunctional, ініціалізує об'єкт PurchaseDao для доступу до бази даних придбань.
     */
    public PurchaseFunctional() {
        purchaseDao = new PurchaseDao();
    }

    GunsDao gunsDao = new GunsDao();

    /**
     * Метод для додавання нового придбання зброї на основі введених користувачем даних.
     *
     * @param userName Ім'я користувача, який робить придбання
     */
    public void addPurchaseFromUserInput(String userName) {
        Scanner scanner = new Scanner(System.in);

        // Показати список доступної зброї
        System.out.println("Список доступної зброї ╾━╤デ╦︻");
        List<Guns> gunsList = gunsDao.getAll();

        if (gunsList.isEmpty()) {
            System.out.println("Список зброї порожній✖\uFE0F");
            return;
        }

        for (int i = 0; i < gunsList.size(); i++) {
            System.out.println((i + 1) + ". " + gunsList.get(i).getName() + " Ціна:" + gunsList.get(i).getPrice());
        }

        // Вибрати зброю для придбання
        System.out.println("Виберіть номер зброї, яку ви хочете придбати:");
        int choice = scanner.nextInt();

        if (choice < 1 || choice > gunsList.size()) {
            System.out.println("Некоректний вибір. Придбання не здійснено❌");
            return;
        }

        Guns selectedGun = gunsList.get(choice - 1);

        // Запитати користувача про кількість придбань
        System.out.println("Введіть кількість придбаних одиниць:");
        int count = scanner.nextInt();

        double countPrice = count * gunsList.get(choice - 1).getPrice();

        Date dateBuy = new Date();

        // Створити об'єкт Purchase та зберегти його
        Purchase newPurchase = new Purchase(selectedGun.getName() + " - " + selectedGun.getType(), userName, count, countPrice, dateBuy);
        purchaseDao.savePurchase(newPurchase);

        System.out.println("Придбання здійснено успішно✅");
        System.out.println(selectedGun.getName() +
                " - " + selectedGun.getType() + "\n" +
                " Кількість: " + count + "\n" +
                " Загальна ціна: " + countPrice + "$");
    }

    /**
     * Метод для виведення на екран всіх придбань конкретного користувача.
     *
     * @param userName Ім'я користувача, для якого вивести придбання
     */
    public void viewPurchase(String userName) {
        List<Purchase> purchaseList = purchaseDao.getPurchasesByUser(userName);

        System.out.println("Список придбаного:");

        for (Purchase purchase : purchaseList) {
            System.out.println("╭───────────────────────────────");
            System.out.println("Придбання: " + purchase.getItem() + "\n" +
                    "Користувач: " + purchase.getUser() + "\n" +
                    "Кількість: " + purchase.getCount() + "\n" +
                    "Загальна ціна: " + purchase.getPrice());
            System.out.println("╰───────────────────────────────");
        }

        if (purchaseList.isEmpty()) {
            System.out.println("Список придбань порожній✖\uFE0F");
        }
    }

    /**
     * Метод для виведення на екран всіх придбань.
     */
    public void viewAllPurchases() {
        List<Purchase> purchaseList = purchaseDao.getAll();

        System.out.println("Список всіх придбань:");

        for (Purchase purchase : purchaseList) {
            System.out.println("Придбання: " + purchase.getItem());
            System.out.println("───────────────────────────────");
        }

        if (purchaseList.isEmpty()) {
            System.out.println("Список придбань порожній✖\uFE0F");
        }
    }
}
