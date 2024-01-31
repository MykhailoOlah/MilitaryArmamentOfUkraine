package com.olag.accountmilitaryarmamentukraine.aui;

import com.olag.accountmilitaryarmamentukraine.persistence.GunsDao;
import com.olag.accountmilitaryarmamentukraine.persistence.entity.Guns;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Клас GunsFunctional містить методи для взаємодії з функціоналом, пов'язаним з зброєю.
 */
public class GunsFunctional {

    private GunsDao gunsDao;

    /**
     * Конструктор класу GunsFunctional, ініціалізує об'єкт GunsDao для доступу до бази даних зброї.
     */
    public GunsFunctional() {
        gunsDao = new GunsDao();
    }

    /**
     * Метод для додавання нової зброї за введеними користувачем даними.
     */
    public void addGunFromUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть тип зброї:");
        String type = scanner.nextLine();

        System.out.println("Введіть назву зброї:");
        String name = scanner.nextLine();

        System.out.println("Введіть ціну зброї:");
        double price = scanner.nextDouble();
        scanner.nextLine(); // очистка буфера введення

        System.out.println("Введіть опис зброї:");
        String description = scanner.nextLine();

        Guns newGun = new Guns(type, name, price, description);

        gunsDao.saveGuns(newGun);

        System.out.println("Зброя додана успішно✅");
    }

    /**
     * Метод для виведення на екран усієї інформації про зброю з бази даних.
     */
    public void viewAllGuns() {
        List<Guns> gunsList = gunsDao.getAll();

        System.out.println("Список всієї зброї:");

        for (Guns gun : gunsList) {
            System.out.println("╭───────────────────────────────");
            System.out.println("Тип: " + gun.getType());
            System.out.println("Назва: " + gun.getName());
            System.out.println("Ціна: " + gun.getPrice() + "$");
            System.out.println("Опис: " + gun.getDescription());
            System.out.println("╰───────────────────────────────");
        }

        if (gunsList.isEmpty()) {
            System.out.println("Список зброї порожній✖\uFE0F");
        }
    }

    /**
     * Метод для редагування інформації про зброю за назвою.
     */
    public void editGunByName() {
        Scanner scanner = new Scanner(System.in);

        viewAllGuns();

        System.out.println("Введіть назву зброї, яку ви хочете редагувати:");
        String gunNameToEdit = scanner.nextLine();

        List<Guns> gunsList = gunsDao.getAll();

        Guns gunToEdit = findGunByName(gunNameToEdit, gunsList);

        if (gunToEdit != null) {
            System.out.println("Введіть новий тип зброї:");
            String newType = scanner.nextLine();
            gunToEdit.setType(newType);

            System.out.println("Введіть нову назву зброї:");
            String newName = scanner.nextLine();
            gunToEdit.setName(newName);

            System.out.println("Введіть нову ціну зброї:");
            double newPrice = scanner.nextDouble();
            gunToEdit.setPrice(newPrice);
            scanner.nextLine(); // очистка буфера введення

            System.out.println("Введіть новий опис зброї:");
            String newDescription = scanner.nextLine();
            gunToEdit.setDescription(newDescription);

            gunsDao.updateGuns(gunToEdit, gunsList.indexOf(gunToEdit));

            System.out.println("Зброя відредагована успішно✅");
        } else {
            System.out.println("Зброю під назвою " + gunNameToEdit + " не знайдено❌");
        }
    }

    /**
     * Метод для видалення зброї за назвою.
     */
    public void deleteGunByName() {
        Scanner scanner = new Scanner(System.in);

        viewAllGuns();

        System.out.println("Введіть назву зброї, яку ви хочете видалити:");
        String gunNameToDelete = scanner.nextLine();

        List<Guns> gunsList = gunsDao.getAll();

        Guns gunToDelete = findGunByName(gunNameToDelete, gunsList);

        if (gunToDelete != null) {
            gunsDao.deleteGuns(gunsList.indexOf(gunToDelete));

            System.out.println("Зброя видалена успішно✅");
        } else {
            System.out.println("Зброю під назвою '" + gunNameToDelete + "' не знайдено❌");
        }
    }

    private Guns findGunByName(String gunName, List<Guns> gunsList) {
        for (Guns gun : gunsList) {
            if (gun.getName().equalsIgnoreCase(gunName)) {
                return gun;
            }
        }
        return null;
    }

    /**
     * Метод для пошуку зброї за назвою.
     */
    public void findGunName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть назву зброї для пошуку: ");
        String searchInput = scanner.nextLine();

        Optional<Guns> foundGun = gunsDao.getGunByName(searchInput);

        if (foundGun.isPresent()) {
            System.out.println("Зброю під назвою '" + searchInput + "' знайдено✅");
            Guns gun = foundGun.get();
            System.out.println("╭───────────────────────────────");
            System.out.println("Тип: " + gun.getType());
            System.out.println("Назва: " + gun.getName());
            System.out.println("Ціна: " + gun.getPrice() + "$");
            System.out.println("Опис: " + gun.getDescription());
            System.out.println("╰───────────────────────────────");
        } else {
            System.out.println("Збрю під назвою '" + searchInput + "' не знайдено❌");
        }
    }
}
