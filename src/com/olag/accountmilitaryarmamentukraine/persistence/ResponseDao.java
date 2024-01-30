package com.olag.accountmilitaryarmamentukraine.persistence;

import com.olag.accountmilitaryarmamentukraine.domain.JsonService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.olag.accountmilitaryarmamentukraine.persistence.entity.Response;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Клас для роботи з базою даних відгуків у системі.
 */
public class ResponseDao {
    private List<Response> responseList;

    /**
     * Конструктор класу, ініціалізує список відгуків з файлу.
     */
    public ResponseDao() {
        responseList = JsonService.jsonResponseList();
    }

    /**
     * Отримує відгук за заданим ідентифікатором.
     *
     * @param id Ідентифікатор відгуку.
     * @return Об'єкт Optional, що містить відгук, якщо він існує.
     */
    public Optional<Response> get(int id) {
        return Optional.ofNullable(responseList.get(id));
    }

    /**
     * Отримує всі відгуки з бази даних.
     *
     * @return Список відгуків.
     */
    public List<Response> getAll() {
        return responseList;
    }

    Path path = Path.of("resources", "ListResponses.json");

    /**
     * Зберігає новий відгук у базі даних.
     *
     * @param response Об'єкт відгуку для збереження.
     */
    public void saveResponse(Response response) {
        responseList.add(response);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String jsonForResponses = gson.toJson(responseList);
        try {
            JsonService.writeString(path, jsonForResponses);
        } catch (IOException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    /**
     * Отримує список відгуків за заданим логіном користувача.
     *
     * @param userName Логін користувача.
     * @return Список відгуків за заданим логіном користувача.
     */
    public List<Response> getResponsesByUser(String userName) {
        return responseList.stream()
                .filter(response -> response.getUserName().equalsIgnoreCase(userName))
                .collect(Collectors.toList());
    }

    /**
     * Оновлює інформацію про відгук у базі даних.
     *
     * @param response   Об'єкт відгуку для оновлення.
     * @param userChoice Індекс відгуку у списку для оновлення.
     */
    public void updateResponse(Response response, int userChoice) {
        responseList.set(userChoice, response);
        Gson gson = new Gson();
        String jsonForResponses = gson.toJson(responseList);
        try {
            JsonService.writeString(path, jsonForResponses);
        } catch (IOException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    /**
     * Видаляє відгук з бази даних.
     *
     * @param userChoice Індекс відгуку у списку для видалення.
     */
    public void deleteResponse(int userChoice) {
        Path path = Path.of("resources", "ListResponses.json");
        responseList.remove(userChoice);
        Gson gson = new Gson();
        String jsonForResponses = gson.toJson(responseList);
        try {
            JsonService.writeString(path, jsonForResponses);
        } catch (IOException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
