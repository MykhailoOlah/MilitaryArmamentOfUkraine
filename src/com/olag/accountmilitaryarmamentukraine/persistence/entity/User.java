package com.olag.accountmilitaryarmamentukraine.persistence.entity;

/**
 * Клас представляє об'єкт "Користувач" у системі.
 */
public class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private boolean haveLic;
    private String role;

    /**
     * Конструктор класу для створення об'єкта "Користувач" з електронною адресою.
     *
     * @param name     Логін користувача.
     * @param password Пароль користувача.
     * @param email    Електронна адреса користувача.
     * @param haveLic  Наявність ліцензії у користувача.
     * @param role     Роль користувача у системі.
     */
    public User(String name, String password, String email, boolean haveLic, String role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.haveLic = haveLic;
        this.role = role;
    }

    /**
     * Отримує ідентифікатор користувача.
     *
     * @return Ідентифікатор користувача.
     */
    public int getId() {
        return id;
    }

    /**
     * Встановлює ідентифікатор користувача.
     *
     * @param id Ідентифікатор користувача.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Отримує логін користувача.
     *
     * @return Логін користувача.
     */
    public String getName() {
        return name;
    }

    /**
     * Встановлює логін користувача.
     *
     * @param name Логін користувача.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Отримує пароль користувача.
     *
     * @return Пароль користувача.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Встановлює пароль користувача.
     *
     * @param password Пароль користувача.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Перевіряє наявність ліцензії у користувача.
     *
     * @return true, якщо у користувача є ліцензія, false - інакше.
     */
    public boolean isHaveLic() {
        return haveLic;
    }

    /**
     * Встановлює наявність ліцензії у користувача.
     *
     * @param haveLic Наявність ліцензії у користувача.
     */
    public void setHaveLic(boolean haveLic) {
        this.haveLic = haveLic;
    }

    /**
     * Отримує роль користувача у системі.
     *
     * @return Роль користувача.
     */
    public String getRole() {
        return role;
    }

    /**
     * Встановлює роль користувача у системі.
     *
     * @param role Роль користувача.
     */
    public void setRole(String role) {
        this.role = role;
    }
}
