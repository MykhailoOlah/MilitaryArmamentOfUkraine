package com.olag.accountmilitaryarmamentukraine.persistence.entity;

/**
 * Клас представляє об'єкт "Гвинтівка" у системі.
 */
public class Guns {
    private int id;
    private String type;
    private String name;
    private double price;
    private String description;

    /**
     * Конструктор класу для створення об'єкта "Гвинтівка".
     *
     * @param type        Тип гвинтівки.
     * @param name        Назва гвинтівки.
     * @param price       Ціна гвинтівки.
     * @param description Опис гвинтівки.
     */
    public Guns(String type, String name, double price, String description) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    /**
     * Отримує ідентифікатор гвинтівки.
     *
     * @return Ідентифікатор гвинтівки.
     */
    public int getId() {
        return id;
    }

    /**
     * Встановлює ідентифікатор гвинтівки.
     *
     * @param id Ідентифікатор гвинтівки.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Отримує тип гвинтівки.
     *
     * @return Тип гвинтівки.
     */
    public String getType() {
        return type;
    }

    /**
     * Встановлює тип гвинтівки.
     *
     * @param type Тип гвинтівки.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Отримує назву гвинтівки.
     *
     * @return Назва гвинтівки.
     */
    public String getName() {
        return name;
    }

    /**
     * Встановлює назву гвинтівки.
     *
     * @param name Назва гвинтівки.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Отримує ціну гвинтівки.
     *
     * @return Ціна гвинтівки.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Встановлює ціну гвинтівки.
     *
     * @param price Ціна гвинтівки.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Отримує опис гвинтівки.
     *
     * @return Опис гвинтівки.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Встановлює опис гвинтівки.
     *
     * @param description Опис гвинтівки.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
