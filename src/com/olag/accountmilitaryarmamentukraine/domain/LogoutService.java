package com.olag.accountmilitaryarmamentukraine.domain;


import com.olag.accountmilitaryarmamentukraine.aui.Menu;

/**
 * Клас виходу з аккаунту
 */
public class LogoutService {
    /**
     * Метод виходу з аккаунту
     */
    public static void logout(){
        System.out.println("Успішний вихід з аккаунту✅");
        Menu.startMenu();
    }
}
