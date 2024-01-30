package com.olag.accountmilitaryarmamentukraine.persistence.entity;

import java.util.Date;

/**
 * Клас представляє об'єкт "Покупка" у системі.
 */
public class Purchase {
    private String item;
    private String user;
    private int count;
    private double price;
    private Date dateBuy;

    /**
     * Конструктор класу для створення об'єкта "Покупка".
     *
     * @param item    Назва товару.
     * @param user    Логін користувача, який здійснив покупку.
     * @param count   Кількість товару, яку купив користувач.
     * @param price   Сума покупки.
     * @param dateBuy Дата та час здійснення покупки.
     */
    public Purchase(String item, String user, int count, double price, Date dateBuy) {
        this.item = item;
        this.user = user;
        this.count = count;
        this.price = price;
        this.dateBuy = dateBuy;
    }

    /**
     * Отримує дату та час здійснення покупки.
     *
     * @return Дата та час покупки.
     */
    public Date getDateBuy() {
        return dateBuy;
    }

    /**
     * Встановлює дату та час здійснення покупки.
     *
     * @param dateBuy Дата та час покупки.
     */
    public void setDateBuy(Date dateBuy) {
        this.dateBuy = dateBuy;
    }

    /**
     * Конструктор класу для створення об'єкта "Покупка" з вказаною назвою товару.
     *
     * @param item Назва товару.
     */
    public Purchase(String item) {
        this.item = item;
    }

    /**
     * Встановлює назву товару.
     *
     * @param item Назва товару.
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * Отримує логін користувача, який здійснив покупку.
     *
     * @return Логін користувача.
     */
    public String getUser() {
        return user;
    }

    /**
     * Встановлює логін користувача, який здійснив покупку.
     *
     * @param user Логін користувача.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Отримує кількість товару, яку купив користувач.
     *
     * @return Кількість товару.
     */
    public int getCount() {
        return count;
    }

    /**
     * Отримує суму покупки.
     *
     * @return Сума покупки.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Встановлює суму покупки.
     *
     * @param price Сума покупки.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Встановлює кількість товару, яку купив користувач.
     *
     * @param count Кількість товару.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Отримує назву товару.
     *
     * @return Назва товару.
     */
    public String getItem() {
        return item;
    }
}
