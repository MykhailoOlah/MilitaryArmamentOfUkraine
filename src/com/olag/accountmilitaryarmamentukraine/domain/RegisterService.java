package com.olag.accountmilitaryarmamentukraine.domain;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.olag.accountmilitaryarmamentukraine.aui.Menu;
import com.olag.accountmilitaryarmamentukraine.persistence.UserDAO;
import com.olag.accountmilitaryarmamentukraine.persistence.entity.User;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class RegisterService {

    static Scanner scanner = new Scanner(System.in);
    static UserDAO userDAO = new UserDAO();

    /**
     * Метод реєстрації
     */
    public static void registration() {
        UserDAO userDao = new UserDAO();
        List<User> users = userDao.getAll();

        System.out.print("Введiть логiн: ");
        Scanner userLoginInput = new Scanner(System.in,
              Charset.forName(System.getProperty("os.name")
                    .toLowerCase().startsWith("win") ? "Windows-1251" : "UTF-8"));
        String userLogin = userLoginInput.nextLine();

        System.out.print("Введiть електронну пошту\uD83D\uDCE7: ");
        String userEmail = scanner.next();

        try {
            for (User usersList : users) {
                if (Objects.equals(usersList.getName(), userLogin) || Objects.equals(
                      usersList.getEmail(), userEmail)) {
                    System.out.println(
                          "Такий аккаунт iснує або введена електронна пошта вже використовується❌");
                    registration();
                }
            }

            System.out.print("Введiть пароль\uD83D\uDD10: ");
            Scanner password = new Scanner(System.in,
                  Charset.forName(System.getProperty("os.name")
                        .toLowerCase().startsWith("win") ? "Windows-1251" : "UTF-8"));
            String userPassword = password.next();

            if (ValidationService.validate(userLogin, userPassword)) {
                String verificationCode = EmailVerificationService.generateAndSendVerificationCode(
                      userEmail);

                System.out.print(
                      "Введіть код підтвердження, відправлений на вашу електронну адресу\uD83D\uDCEC: ");
                String inputCode = scanner.next();

                Boolean verifyCode = EmailVerificationService.verifyCode(inputCode,
                      verificationCode);
                if (verifyCode) {
                    String hashedUserPassword = BCrypt.withDefaults()
                          .hashToString(12, userPassword.toCharArray());
                    User newUser = new User(userLogin, hashedUserPassword, userEmail, false,
                          "User");
                    users.add(newUser);
                    JsonService.userAdd(users);

                    System.out.println("Аккаунт успiшно створено✅");

                    System.out.println("Електронна пошта успішно підтверджена✅");
                    Menu.mainMenu(userLogin);
                } else {
                    System.out.println(
                          "Помилка! Верифікаційний код введено неправильно, або невчасно. Повторіть спробу❌");
                }
                Menu.startMenu();
            } else {
                System.out.println("Логiн та пароль має мiстити не менше 5 символiв❌");
                Menu.startMenu();
            }

        } catch (Exception e) {
            System.out.println("Логiн та пароль має мiстити не менше 5 символiв❌");
        }
    }
}
