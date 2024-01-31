package com.olag.accountmilitaryarmamentukraine.aui;

import com.olag.accountmilitaryarmamentukraine.persistence.GunsDao;
import com.olag.accountmilitaryarmamentukraine.persistence.ResponseDao;
import com.olag.accountmilitaryarmamentukraine.persistence.UserDAO;
import com.olag.accountmilitaryarmamentukraine.persistence.entity.Guns;
import com.olag.accountmilitaryarmamentukraine.persistence.entity.Response;
import com.olag.accountmilitaryarmamentukraine.persistence.entity.User;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Клас ResponseFunctional містить методи для взаємодії з функціоналом, пов'язаним із відгуками користувачів.
 */
public class ResponseFunctional {

    private ResponseDao responseDao;

    /**
     * Конструктор класу ResponseFunctional, ініціалізує об'єкт ResponseDao для доступу до бази даних відгуків.
     */
    public ResponseFunctional() {
        responseDao = new ResponseDao();
    }

    GunsDao gunsDao = new GunsDao();

    /**
     * Метод для додавання нового відгуку на основі введених користувачем даних.
     *
     * @param userName Ім'я користувача, який залишає відгук
     */
    public void addResponseFromUserInput(String userName) {
        Scanner scanner = new Scanner(System.in);

        viewAllGuns();

        List<Guns> gunsList = gunsDao.getAll();

        System.out.println("Введіть номер зброї, якій ви хочете залишити відгук:");
        int gunIndex;

        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Невірний номер зброї❌. Будь ласка, введіть ціле число.");
                scanner.next();
            }
            gunIndex = scanner.nextInt();
            scanner.nextLine();

            if (gunIndex < 1 || gunIndex > gunsList.size()) {
                System.out.println("Невірний номер зброї❌. Будь ласка, введіть дійсний номер зброї.");
            }
        } while (gunIndex < 1 || gunIndex > gunsList.size());

        System.out.println("Введіть відгук:");
        String responseText = scanner.nextLine();

        Guns selectedGun = gunsList.get(gunIndex - 1);

        Date dateCreate = new Date();

        Response newResponse = new Response(responseText, userName, selectedGun.getName(), selectedGun.getType(), dateCreate);

        responseDao.saveResponse(newResponse);

        System.out.println("Відгук додано успішно✅");
    }

    /**
     * Метод для виведення на екран всіх відгуків користувача.
     */
    public void viewAllGuns() {
        List<Guns> gunsList = gunsDao.getAll();  // Отримуємо список зброї з Dao

        if(gunsList.isEmpty()){
            System.out.println("Список зброї порожній✖\uFE0F");
        }

        System.out.println("Список всієї зброї:");

        for (int i = 0; i < gunsList.size(); i++) {
            Guns gun = gunsList.get(i);
            System.out.println("╭───────────────────────────────");
            System.out.println((i + 1) + ". Тип: " + gun.getType());
            System.out.println("   Назва: " + gun.getName());
            System.out.println("   Ціна: " + gun.getPrice() + "$");
            System.out.println("   Опис: " + gun.getDescription());
            System.out.println("╰───────────────────────────────");
        }

        if (gunsList.isEmpty()) {
            System.out.println("Список зброї порожній✖\uFE0F");
        }
    }


    /**
     * Метод для виведення на екран всіх відгуків користувача.
     */
    public void viewAllResponses() {
        List<Response> userResponses = responseDao.getAll();

        if(userResponses.isEmpty()){
            System.out.println("Відгуків не знайдено❌");
        }

        System.out.println("Відгуки:");

        for (int i = 0; i < userResponses.size(); i++) {
            Response response = userResponses.get(i);
            System.out.println("╭───────────────────────────────");
            System.out.println((i + 1) + ". Зброя: " + response.getGun());
            System.out.println("   Тип зброї: " + response.getTypeGun());
            System.out.println("   Відгук: " + response.getResponse());
            System.out.println("   Дата створення: " + response.getDateCreate());
            System.out.println("   Відгук залишив: " + response.getUserName());
            System.out.println("╰───────────────────────────────");
        }

        if (userResponses.isEmpty()) {
            System.out.println("Відгуки відсутні✖\uFE0F");
        }
    }

    /**
     * Метод для редагування відгуку користувача.
     *
     * @param userName Ім'я користувача, який редагує відгук
     */
    public void editResponseByUser(String userName) {
        Scanner scanner = new Scanner(System.in);

        List<Response> userResponses = responseDao.getResponsesByUser(userName);

        if (userResponses.isEmpty()) {
            System.out.println("У вас немає жодних відгуків для редагування❌");
            return;
        }

        viewAllResponses(); // Показати всі відгуки користувача

        System.out.println("Введіть номер відгуку, який ви хочете редагувати (від 1 до " + userResponses.size() + "):");

        int responseIndex;

        while (true) {
            try {
                responseIndex = Integer.parseInt(scanner.nextLine());

                if (responseIndex >= 1 && responseIndex <= userResponses.size()) {
                    break; // exit the loop if the input is valid
                } else {
                    System.out.println("Невірний номер відгуку. Будь ласка, введіть число від 1 до " + userResponses.size() + ":");
                }
            } catch (NumberFormatException e) {
                System.out.println("Невірний формат. Будь ласка, введіть число:");
            }
        }

        Response selectedResponse = userResponses.get(responseIndex - 1);

        System.out.println("Введіть новий відгук:");
        String newResponseText = scanner.nextLine();

        // Оновити дані в об'єкті Response
        selectedResponse.setResponse(newResponseText);

        // Оновити відгук в Dao
        responseDao.updateResponse(selectedResponse, userResponses.indexOf(selectedResponse));

        System.out.println("Відгук відредаговано успішно✅");
    }

    /**
     * Метод для видалення відгуку користувача.
     *
     * @param userName Ім'я користувача, який видаляє відгук
     */
    public void deleteResponseByUser(String userName) {
        Scanner scanner = new Scanner(System.in);

        viewAllResponses(); // Показати всі відгуки користувача

        System.out.println("Введіть номер відгуку, який ви хочете видалити:");
        int responseIndex = scanner.nextInt();
        scanner.nextLine(); // очистка буфера введення

        List<Response> userResponses = responseDao.getResponsesByUser(userName);

        if (responseIndex >= 1 && responseIndex <= userResponses.size()) {
            // Видалити відгук з Dao
            responseDao.deleteResponse(userResponses.indexOf(userResponses.get(responseIndex - 1)));

            System.out.println("Відгук видалено успішно✅");
        } else {
            System.out.println("Невірний номер відгуку❌");
        }
    }

    /**
     * Метод для надання ліцензії користувачеві.
     */
    public void grantLicenseToUser() {
        UserDAO userDAO = new UserDAO();

        List<User> usersWithoutLicense = userDAO.getUsersWithoutLicense();

        if (usersWithoutLicense.isEmpty()) {
            System.out.println("Всі користувачі мають ліцензію✅");
        } else {
            System.out.println("Список користувачів без ліцензій:");
            for (int i = 0; i < usersWithoutLicense.size(); i++) {
                System.out.println((i + 1) + ". " + usersWithoutLicense.get(i).getName());
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("Виберіть номер користувача, якому хочете надати ліцензію:");
            int userIndex = scanner.nextInt();

            if (userIndex >= 1 && userIndex <= usersWithoutLicense.size()) {
                User selectedUser = usersWithoutLicense.get(userIndex - 1);
                // Логіка для надання ліцензії обраному користувачеві
                selectedUser.setHaveLic(true);

                userDAO.grantLicense(selectedUser.getName());

                System.out.println("Ліцензія надана користувачу " + selectedUser.getName());
            } else {
                System.out.println("Невірний номер користувача❌");

            }
        }
    }

    private Response findResponseByUserName(String userName, List<Response> responseList) {
        for (Response response : responseList) {
            if (response.getUserName().equalsIgnoreCase(userName)) {
                return response;
            }
        }
        return null;
    }
}
