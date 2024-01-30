package com.olag.accountmilitaryarmamentukraine.persistence.entity;

import java.util.Date;

/**
 * Клас представляє об'єкт "Відгук" у системі.
 */
public class Response {
    private String response;
    private String userName;
    private String gun;
    private String typeGun;
    private Date dateCreate;

    /**
     * Конструктор класу для створення об'єкта "Відгук".
     *
     * @param response   Текст відгуку.
     * @param userName   Логін користувача, який залишив відгук.
     * @param gun        Назва товару, до якого відноситься відгук.
     * @param typeGun    Тип товару (гвинтівка, патрон і т.д.).
     * @param dateCreate Дата та час створення відгуку.
     */
    public Response(String response, String userName, String gun, String typeGun, Date dateCreate) {
        this.response = response;
        this.userName = userName;
        this.gun = gun;
        this.typeGun = typeGun;
        this.dateCreate = dateCreate;
    }

    /**
     * Отримує текст відгуку.
     *
     * @return Текст відгуку.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Встановлює текст відгуку.
     *
     * @param response Текст відгуку.
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * Отримує логін користувача, який залишив відгук.
     *
     * @return Логін користувача.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Встановлює логін користувача, який залишив відгук.
     *
     * @param userName Логін користувача.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Отримує назву товару, до якого відноситься відгук.
     *
     * @return Назва товару.
     */
    public String getGun() {
        return gun;
    }

    /**
     * Встановлює назву товару, до якого відноситься відгук.
     *
     * @param gun Назва товару.
     */
    public void setGun(String gun) {
        this.gun = gun;
    }

    /**
     * Отримує тип товару (гвинтівка, патрон і т.д.).
     *
     * @return Тип товару.
     */
    public String getTypeGun() {
        return typeGun;
    }

    /**
     * Встановлює тип товару (гвинтівка, патрон і т.д.).
     *
     * @param typeGun Тип товару.
     */
    public void setTypeGun(String typeGun) {
        this.typeGun = typeGun;
    }

    /**
     * Отримує дату та час створення відгуку.
     *
     * @return Дата та час створення відгуку.
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * Встановлює дату та час створення відгуку.
     *
     * @param dateCreate Дата та час створення відгуку.
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
}
