package com.olag.accountmilitaryarmamentukraine.persistence;

import java.util.List;
import java.util.Optional;

/**
 * Інтерфейс продуктів
 * @param <Inventory> Колекція продуктів
 */
public interface Dao<Inventory> {

    Optional<Inventory> get(int id);

    List<Inventory> getAll();
}