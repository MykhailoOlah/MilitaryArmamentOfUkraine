package com.olag.accountmilitaryarmamentukraine.aui;

import com.olag.accountmilitaryarmamentukraine.domain.AuthorizationService;
import com.olag.accountmilitaryarmamentukraine.domain.RegisterService;
import com.olag.accountmilitaryarmamentukraine.persistence.GunsDao;
import com.olag.accountmilitaryarmamentukraine.persistence.UserDAO;
import java.util.Optional;
import java.util.Scanner;


/**
 * Клас Menu представляє собою меню для взаємодії з функціоналом програми з обліку військового озброєння України.
 */
public class Menu {

    private static final GunsDao gunsDao = new GunsDao();
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Метод startMenu виводить початкове меню та обробляє вибір користувача при старті програми.
     */
    public static void startMenu() {
        int menuChoice = 0;
        Scanner choice = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                  + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                  + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣿⣿⣿⣿⣿⣿⠉⠀  ⠀       ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀⠀⠀\n"
                  + "⠀⠀⠀⠀⠀⠀⠀⢀⣤⣶⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⣀⣴⣿⣿⣭⣭⣭⣭⣿⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣴⣯⣧⣀⣀⣀⣀⣀⣤\n"
                  + "⠀⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿⠿⠟⠉⠉⠉⠉⠉⠉⠉⠉\n"
                  + "⠀⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣹⣿⣿⡟⠋⢁⣴⣿⡿⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                  + "⠀⠀⠀⠀⠀⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⡿⠿⢀⣴⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                  + "⠀⠀⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⣤⣤⣶⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                  + "⠀⠀⠀⠀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                  + "⠀  ⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⣙⣿⣿⣿⣿⣿⣿⣿⡿⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
            System.out.println("• ҉─╤╦︻\uD83D\uDFE8\uD83D\uDFE6Радий вітати в бліку військового озброєння України\uD83D\uDFE6\uD83D\uDFE8︻╦╤─҉ •");
            System.out.println("[1] Авторизацiя\uD83D\uDC68\uD83C\uDFFC\u200D\uD83D\uDCBB");
            System.out.println("[2] Реєстрацiя\uD83D\uDCDD");
            System.out.println("[3] Перегляд списку зброї\uD83D\uDC40");
            System.out.println("[4] Вихiд\uD83D\uDEAA\uD83D\uDEB6\uD83C\uDFFC");
            while (!choice.hasNextInt()) {
                System.out.println("Введено хибне значення. Будь ласка, введіть число.");
                choice.next();
            }

            menuChoice = choice.nextInt();

            switch (menuChoice) {
                case 1:
                    AuthorizationService.authorization();
                    break;
                case 2:
                    RegisterService.registration();
                    break;
                case 3:
                    GunsFunctional gunsFunctional = new GunsFunctional();
                    gunsFunctional.viewAllGuns();
                    break;
                case 4:
                    System.out.println("Дякуємо що скористалися нашими послугами. Слава Україні\uD83D\uDFE6\uD83D\uDFE8");
                    System.exit(4);
                    break;
                default:
                    System.out.println("Введено хибне значення. Будь ласка, введіть коректне число.");
                    break;
            }
        } while (menuChoice != 4);
    }

    /**
     * Метод mainMenu представляє головне меню програми, де користувач може вибирати опції.
     *
     * @param userName Ім'я користувача, для якого відображається головне меню.
     */
    public static void mainMenu(String userName) {
        int menuChoice = 0;
        GunsFunctional gunsFunctional = new GunsFunctional();
        UserDAO userDAO = new UserDAO();

        do {
            System.out.println();
            System.out.println("• ҉─╤╦︻🟨🟦Облік військового озброєння України🟦🟨︻╦╤─҉ •");
            System.out.println("[1] Перегляд зброї👀");
            System.out.println("[2] Пошук зброї🔍");
            System.out.println("[3] Кошик🛒");
            System.out.println("[4] Відгуки🖊️");

            // Перевірка, чи користувач є адміністратором
            if (isUserAdmin(userName)) {
                System.out.println("[5] Редагування📝");
                System.out.println("[6] Надання ліцензії✅");
            }

            // Перевірка, чи користувач має ліцензію
            if (userDAO.getLicenseStatusByUsername(userName).orElse(false)) {
                System.out.println("[7] Вихід з акаунту🚪🚶🏼");
            } else {
                System.out.println("[7] Вихід з акаунту (У вас немає ліцензії на зброю)🚪🚶🏼");
            }

            try {
                menuChoice = Integer.parseInt(scanner.nextLine());

                ResponseFunctional responseFunctional = new ResponseFunctional();

                switch (menuChoice) {
                    case 1:
                        gunsFunctional.viewAllGuns();
                        break;
                    case 2:
                        gunsFunctional.findGunName();
                        break;
                    case 3:
                        // Перевірка, чи користувач має ліцензію перед переходом в кошик
                        if (userDAO.getLicenseStatusByUsername(userName).orElse(false)) {
                            viewCart(userName);
                        } else {
                            System.out.println("У вас немає ліцензії на зброю ( ಠ ʖ̯ ಠ)╭∩╮");
                        }
                        break;
                    case 4:
                        showWorkWithReviews(userName);
                        break;
                    case 5:
                        if (isUserAdmin(userName)) {
                            addedMenu(userName);
                        } else {
                            System.out.println("Невірний вибір❌");
                        }
                        break;
                    case 6:
                        if (isUserAdmin(userName)) {
                            responseFunctional.grantLicenseToUser();
                            mainMenu(userName);
                        } else {
                            System.out.println("Невірний вибір❌");
                        }
                        break;
                    case 7:
                        System.out.println("Дякуємо що скористалися нашими послугами. Слава Україні🟡🔵");
                        startMenu();
                        break;
                    default:
                        System.out.println("Введено хибні дані❌. Будь ласка, введіть число від 1 до 7.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Введено хибні дані❌. Будь ласка, введіть число від 1 до 7.");
            }
        } while (menuChoice != 7);
    }


    private static void showWorkWithReviews(String userName) {
        System.out.println();
        System.out.println("Відгуки\uD83D\uDD8A\uFE0F");
        System.out.println("[1] Всі відгуки");
        System.out.println("[2] Додати відгук");
        System.out.println("[3] Назад");

        int menuChoice = getUserInputAsInt();
        ResponseFunctional responseFunctional = new ResponseFunctional();

        switch (menuChoice) {
            case 1:
                responseFunctional.viewAllResponses();
                showWorkWithReviews(userName);
                break;
            case 2:
                responseFunctional.addResponseFromUserInput(userName);
                showWorkWithReviews(userName);
                break;
            case 3:
                mainMenu(userName);
                break;
            default:
                System.out.println("Введено хибні дані❌");
                break;
        }
    }

    private static void viewCart(String userName) {
        int cartChoice = 0;

        do {
            System.out.println();
            System.out.println("Кошик\uD83D\uDED2");
            System.out.println("[1] Вибрати зброю для покупки");
            System.out.println("[2] Переглянути список покупок");
            System.out.println("[3] Назад");

            cartChoice = getUserInputAsInt();
            PurchaseFunctional purchaseFunctional = new PurchaseFunctional();

            switch (cartChoice) {
                case 1:
                    purchaseFunctional.addPurchaseFromUserInput(userName);
                    break;
                case 2:
                    purchaseFunctional.viewPurchase(userName);
                    break;
                case 3:
                    mainMenu(userName);
                    break;
                default:
                    System.out.println("Введено хибні дані❌");
                    break;
            }
        } while (cartChoice != 3);
    }


    private static int getUserInputAsInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Введено хибне значення❌ Введіть число!");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }
    private static boolean isUserAdmin(String username) {
        UserDAO userDAO = new UserDAO();
        Optional<String> role = userDAO.getRoleByUsername(username);

        // Перевірка, чи роль користувача є адміністратором
        return role.isPresent() && role.get().equalsIgnoreCase("admin");
    }


    private static void addedMenu(String userName) {
        int adminChoice = 0;

        do {
            System.out.println();
            System.out.println("Редагування\uD83D\uDCDD");
            System.out.println("[1] Редагування зброї");
            System.out.println("[2] Редагування відгуків");
            System.out.println("[3] Назад");

            adminChoice = getUserInputAsInt();

            switch (adminChoice) {
                case 1:
                    editWeaponsMenu(userName);
                    break;
                case 2:
                    editReviews(userName);
                    break;
                case 3:
                    mainMenu(userName);
                    break;
                default:
                    System.out.println("Введено хибні дані❌");
                    break;
            }
        } while (adminChoice != 3);
    }

    private static void editWeaponsMenu(String userName) {
        int editChoice = 0;

        do {
            System.out.println();
            System.out.println("Редагування зброї\uD83D\uDCDD");
            System.out.println("[1] Додати зброю");
            System.out.println("[2] Редагувати зброю");
            System.out.println("[3] Видалити зброю");
            System.out.println("[4] Переглянути всю зброю");
            System.out.println("[5] Назад");

            editChoice = getUserInputAsInt();
            GunsFunctional gunsFunctional = new GunsFunctional();

            switch (editChoice) {
                case 1:
                    gunsFunctional.addGunFromUserInput();
                    break;
                case 2:
                    gunsFunctional.editGunByName();
                    break;
                case 3:
                    gunsFunctional.deleteGunByName();
                    break;
                case 4:
                    gunsFunctional.viewAllGuns();
                    break;
                case 5:
                    addedMenu(userName);
                    break;
                default:
                    System.out.println("Введено хибні дані❌");
                    break;
            }
        } while (editChoice != 5);
    }

    private static void editReviews(String userName) {
        int editChoice = 0;

        do {
            System.out.println();
            System.out.println("Редагування відгуків\uD83D\uDCDD");
            System.out.println("[1] Додати відгук");
            System.out.println("[2] Редагувати відгук");
            System.out.println("[3] Видалити відгук");
            System.out.println("[4] Переглянути всі відгуки");
            System.out.println("[5] Назад");

            editChoice = getUserInputAsInt();

            ResponseFunctional responseFunctional = new ResponseFunctional();

            switch (editChoice) {
                case 1:
                    responseFunctional.addResponseFromUserInput(userName);
                    break;
                case 2:
                    responseFunctional.editResponseByUser(userName);
                    break;
                case 3:
                    responseFunctional.deleteResponseByUser(userName);
                    break;
                case 4:
                    responseFunctional.viewAllResponses();
                    break;
                case 5:
                    addedMenu(userName);
                    break;
                default:
                    System.out.println("Введено хибні дані❌");
                    break;
            }
        } while (editChoice != 5);
    }
}