package com.olag.accountmilitaryarmamentukraine.domain;

/**
 * Клас валідації
 */
public class ValidationService {
    /**
     * Метод валідації
     * @param userLogin Ім'я користувача
     * @param userPassword Пароль користувача
     * @return Умову валідації
     */
    public static boolean validate(String userLogin, String userPassword){
        return userLogin.matches("[A-Za-z0-9]{5,}")  && userPassword.matches("[A-Za-z0-9]{5,}");
    }
}
