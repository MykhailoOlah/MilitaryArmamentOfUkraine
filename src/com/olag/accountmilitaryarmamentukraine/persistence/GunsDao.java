package com.olag.accountmilitaryarmamentukraine.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.olag.accountmilitaryarmamentukraine.domain.JsonService;
import com.olag.accountmilitaryarmamentukraine.persistence.entity.Guns;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Клас для роботи з базою даних гвинтівок у системі.
 */
public class GunsDao {
    private List<Guns> gunsList;
    /**
     * Конструктор класу, ініціалізує список гвинтівок з файлу.
     */
    public GunsDao() {
        gunsList = JsonService.jsonGunsList();
    }
    /**
     * Отримує гвинтівку за заданим ідентифікатором.
     *
     * @param id Ідентифікатор гвинтівки.
     * @return Об'єкт Optional, що містить гвинтівку, якщо вона існує.
     */
    public Optional<Guns> get(int id) {
        return Optional.ofNullable(gunsList.get(id));
    }
    /**
     * Отримує всі гвинтівки з бази даних.
     *
     * @return Список гвинтівок.
     */
    public List<Guns> getAll() {
        return gunsList;
    }

    Path path = Path.of("resources", "ListGuns.json");
    /**
     * Зберігає нову гвинтівку у базі даних.
     *
     * @param guns Об'єкт гвинтівки для збереження.
     */
    public void saveGuns(Guns guns) {
        gunsList.add(guns);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String jsonForGuns = gson.toJson(gunsList);
        try {
            JsonService.writeString(path, jsonForGuns);
        } catch (IOException e) {
            System.out.println("Помилка❌ " + e.getMessage());
        }
    }
    /**
     * Оновлює інформацію про гвинтівку у базі даних.
     *
     * @param guns       Об'єкт гвинтівки для оновлення.
     * @param userChoice Індекс гвинтівки у списку для оновлення.
     */
    public void updateGuns(Guns guns, int userChoice) {
        gunsList.set(userChoice, guns);
        Gson gson = new Gson();
        String jsonForGuns = gson.toJson(gunsList);
        try {
            JsonService.writeString(path, jsonForGuns);
        } catch (IOException e) {
            System.out.println("Помилка❌ " + e.getMessage());
        }
    }

    /**
     * Видаляє гвинтівку з бази даних.
     *
     * @param userChoice Індекс гвинтівки у списку для видалення.
     */
    public void deleteGuns(int userChoice) {
        Path path = Path.of("resources", "ListGuns.json");
        gunsList.remove(userChoice);
        Gson gson = new Gson();
        String jsonForGuns = gson.toJson(gunsList);
        try {
            JsonService.writeString(path, jsonForGuns);
        } catch (IOException e) {
            System.out.println("Помилка❌ " + e.getMessage());
        }
    }
    /**
     * Отримує гвинтівку за заданою назвою.
     *
     * @param name Назва гвинтівки.
     * @return Об'єкт Optional, що містить гвинтівку за заданою назвою, якщо вона існує.
     */
    public Optional<Guns> getGunByName(String name) {
        return gunsList.stream()
                .filter(gun -> gun.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    /**
     * Отримує список гвинтівок за заданим типом.
     *
     * @param type Тип гвинтівки.
     * @return Список гвинтівок за заданим типом.
     */
    public List<Guns> getGunsByType(String type) {
        return gunsList.stream()
                .filter(gun -> gun.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
}
