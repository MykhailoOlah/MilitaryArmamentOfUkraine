package com.olag.accountmilitaryarmamentukraine.persistence;

import static com.olag.accountmilitaryarmamentukraine.domain.JsonService.jsonUserList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.olag.accountmilitaryarmamentukraine.persistence.entity.User;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Клас UserDao відповідає за доступ до даних користувачів та виконання операцій з ними.
 * Використовується для отримання списку користувачів із зовнішнього джерела даних.
 */
public class UserDAO implements Dao<User> {

    private List<User> userList;

    /**
     * Конструктор класу UserDao ініціалізує список користувачів на основі зчитаних даних із зовнішнього джерела.
     */
    public UserDAO() {
        userList = jsonUserList();
    }

    /**
     * Отримати користувача за ідентифікатором (ID).
     *
     * @param id Ідентифікатор користувача
     * @return Optional, що може містити користувача з вказаним ідентифікатором або пустий, якщо користувача не знайдено
     */
    @Override
    public Optional<User> get(int id) {
        return Optional.empty();
    }

    /**
     * Отримати список усіх користувачів.
     *
     * @return Список користувачів
     */
    @Override
    public List<User> getAll() {
        return userList;
    }

    /**
     * Отримати роль користувача за логіном.
     *
     * @param username Логін користувача
     * @return Optional, що може містити роль користувача за вказаним логіном або пустий, якщо користувача не знайдено
     */
    public Optional<String> getRoleByUsername(String username) {
        for (User user : userList) {
            if (user.getName().equals(username)) {
                return Optional.ofNullable(user.getRole());
            }
        }
        return Optional.empty();
    }

    /**
     * Отримати статус ліцензії користувача за логіном.
     *
     * @param username Логін користувача
     * @return Optional, що може містити статус ліцензії користувача за вказаним логіном або пустий, якщо користувача не знайдено
     */
    public Optional<Boolean> getLicenseStatusByUsername(String username) {
        for (User user : userList) {
            if (user.getName().equals(username)) {
                return Optional.of(user.isHaveLic());
            }
        }
        return Optional.empty();
    }

    /**
     * Отримати список користувачів без ліцензії.
     *
     * @return Список користувачів без ліцензії
     */
    public List<User> getUsersWithoutLicense() {
        return userList.stream()
                .filter(user -> !user.isHaveLic())
                .collect(Collectors.toList());
    }

    Path path = Path.of("resources", "User.json");

    /**
     * Зберегти користувачів у файл.
     */
    public void saveUsersToFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonForUsers = gson.toJson(userList);
        try {
            Files.writeString(path, jsonForUsers);
        } catch (IOException e) {
            System.out.println("Помилка при збереженні даних у файл: " + e.getMessage());
        }
    }

    /**
     * Надати ліцензію користувачу за логіном.
     *
     * @param username Логін користувача
     */
    public void grantLicense(String username) {
        userList.stream()
                .filter(user -> user.getName().equals(username))
                .findFirst()
                .ifPresent(user -> {
                    user.setHaveLic(true);
                    saveUsersToFile(); // Зберегти оновлений список користувачів
                });
    }
}
