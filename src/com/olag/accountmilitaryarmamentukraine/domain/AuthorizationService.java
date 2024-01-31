package com.olag.accountmilitaryarmamentukraine.domain;

import static com.olag.accountmilitaryarmamentukraine.domain.RegisterService.scanner;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.olag.accountmilitaryarmamentukraine.aui.Menu;
import com.olag.accountmilitaryarmamentukraine.persistence.UserDAO;
import com.olag.accountmilitaryarmamentukraine.persistence.entity.User;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Клас для авторизації користувачів в програмі.
 */
public class AuthorizationService {

    private static final UserDAO userDAO = new UserDAO();

    /**
     * Авторизує користувача в програму.
     */
    public static void authorization() {
        while (true) {
            System.out.println("Введіть логін: ");
            Scanner userLoginInput = new Scanner(System.in,
                    Charset.forName(System.getProperty("os.name")
                            .toLowerCase().startsWith("win") ? "Windows-1251" : "UTF-8"));
            try {
                String userLogin = userLoginInput.nextLine();
                System.out.println("Введіть пароль: ");
                Scanner password = new Scanner(System.in,
                    Charset.forName(System.getProperty("os.name")
                        .toLowerCase().startsWith("win") ? "Windows-1251" : "UTF-8"));
                String userPassword = password.nextLine();

                System.out.println("Введіть електронну пошту: ");
                String userEmail = scanner.next();

                String role = String.valueOf(userDAO.getRoleByUsername(userLogin));

                Optional<Boolean> licenseStatusOptional = userDAO.getLicenseStatusByUsername(userLogin);

                boolean haveLic = licenseStatusOptional.get();

                User user = new User(userLogin, userPassword, userEmail, haveLic, role);

                if (isExistUser(user) == 1) {
                    System.out.println("Авторизація успішна!");
                    Menu.mainMenu(userLogin);
                    break;
                } else if (isExistUser(user) == 2) {
                    System.out.println("Не вірний логін або пароль!");
                    Menu.startMenu();
                }
            } catch (Exception e) {
                System.out.println("Помилка, спробуйте ще раз!");
            }
        }
    }

    /**
     * Перевіряє наявність користувача в системі.
     *
     * @param user Користувач, для якого виконується перевірка.
     * @return 1 - Аккаунт існує, 2 - Аккаунт не існує.
     */
    public static int isExistUser(User user) {
        List<User> userList = JsonService.jsonUserList();
        for (User userFromList : userList) {
            if (userFromList.getName().equals(user.getName()) &&
                    BCrypt.verifyer().verify(user.getPassword().toCharArray(),
                            userFromList.getPassword()).verified) {
                return 1;
            }
        }
        return 2;
    }
}
