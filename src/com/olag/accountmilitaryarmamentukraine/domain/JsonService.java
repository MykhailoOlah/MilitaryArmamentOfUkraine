package com.olag.accountmilitaryarmamentukraine.domain;

import com.google.gson.Gson;
import com.olag.accountmilitaryarmamentukraine.persistence.entity.Guns;
import com.olag.accountmilitaryarmamentukraine.persistence.entity.Purchase;
import com.olag.accountmilitaryarmamentukraine.persistence.entity.Response;
import com.olag.accountmilitaryarmamentukraine.persistence.entity.User;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Клас роботи з Json
 */
public class JsonService {
    static Gson gson = new Gson();

    /**
     * Зчитування json
     * @param path шлях до файлу
     * @return Зчитаний файл
     * @throws IOException Ігнорування помилки при роботі з файлами
     */
    public static String readString(Path path) throws IOException {
        return Files.readString(path);
    }

    /**
     * Запис у файл
     * @param path Шлях до файлу
     * @param data Строка яка записується
     * @throws IOException Ігнорування помилки при роботі з файлами
     */
    public static void writeString(Path path, String data) throws IOException {
        Files.writeString(path, data, StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE, StandardOpenOption.CREATE);
    }
    /**
     * Отримання списку предметів інвентаря
     * @return Список предметів інвентаря
     */
    public static List<Guns> jsonInventoryItemList() {
        Path pathToFile = Path.of("resources", "ListGuns.json");
        String jsonToArray = null;
        try {
            if (Files.notExists(pathToFile) || Files.size(pathToFile) == 0) {
                // Якщо файл не існує або пустий, створіть його та додайте початкові дані
                List<Guns> initialGuns = new ArrayList<>();
                initialGuns.add(new Guns("Холодна зброя", "Ніж", 12, "Охотнічий ніж"));
                writeString(pathToFile, gson.toJson(initialGuns));
            }
            jsonToArray = readString(pathToFile);
        } catch (IOException e) {
            System.out.println("Помилка: " + e.getMessage());
        }

        if (jsonToArray != null) {
            Guns[] inventoryItems = gson.fromJson(jsonToArray, Guns[].class);
            if (inventoryItems != null) {
                return new ArrayList<>(Arrays.asList(inventoryItems));
            } else {
                System.out.println("Помилка при парсингу JSON: inventoryItems є null");
            }
        } else {
            System.out.println("Помилка при читанні JSON файлу: jsonToArray є null");
        }

        return new ArrayList<>(); // Повертаємо пустий список, якщо виникла помилка
    }
    /**
     * Отримання списку користувачів
     * @return Список корисутвачів
     */
    public static List<User> jsonUserList() {
        Path pathToFile = Path.of("resources", "User.json");
        String jsonToArray = null;
        try {
            if (readString(pathToFile).length() == 0) {
                List<User> initialUsers = new ArrayList<>();
                writeString(pathToFile, gson.toJson(initialUsers));
            }
            jsonToArray = readString(pathToFile);
        } catch (IOException e) {
            System.out.println("Error is: " + e.getMessage());
        }
        User[] users = gson.fromJson(jsonToArray, User[].class);
        return new ArrayList<>(Arrays.asList(users));
    }
    public static List<Purchase> jsonPurchaseList() {
        Path pathToFile = Path.of("resources", "ListPurchase.json");
        String jsonToArray = null;
        try {
            if (readString(pathToFile).length() == 0) {
                List<Purchase> initialPurchases = new ArrayList<>();
                writeString(pathToFile, gson.toJson(initialPurchases));
            }
            jsonToArray = readString(pathToFile);
        } catch (IOException e) {
            System.out.println("Error is: " + e.getMessage());
        }
        Purchase[] purchases = gson.fromJson(jsonToArray, Purchase[].class);
        return new ArrayList<>(Arrays.asList(purchases));
    }


    public static List<Response> jsonResponseList() {
        Path pathToFile = Path.of("resources", "ListResponses.json");
        String jsonToArray = null;
        try {
            if (readString(pathToFile).length() == 0) {
                List<Response> initialResponses = new ArrayList<>();
                writeString(pathToFile, gson.toJson(initialResponses));
            }
            jsonToArray = readString(pathToFile);
        } catch (IOException e) {
            System.out.println("Error is: " + e.getMessage());
        }
        Response[] responses = gson.fromJson(jsonToArray, Response[].class);
        return new ArrayList<>(Arrays.asList(responses));
    }

    public static List<Guns> jsonGunsList() {
        Path pathToFile = Path.of("resources", "ListGuns.json");
        String jsonToArray = null;
        try {
            if (readString(pathToFile).length() == 0) {
                List<Guns> initialGuns = new ArrayList<>();
                writeString(pathToFile, gson.toJson(initialGuns));
            }
            jsonToArray = readString(pathToFile);
        } catch (IOException e) {
            System.out.println("Помилка: " + e.getMessage());
        }

        if (jsonToArray != null) {
            Guns[] guns = gson.fromJson(jsonToArray, Guns[].class);
            return new ArrayList<>(Arrays.asList(guns));
        } else {
            return new ArrayList<>(); // or handle it accordingly
        }
    }




    /**
     * Додавання користувачів
     * @param userList Колекція користувачів
     */
    public static void userAdd(List<User> userList){
        Path pathToFile = Path.of("resources", "User.json");
        Gson gson = new Gson();
        String usersToStr = gson.toJson(userList);
        try {
            writeString(pathToFile, usersToStr);
        }
        catch (IOException e){
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}