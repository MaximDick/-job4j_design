package ru.job4j.db.sqltracker;

import ru.job4j.tracker.Item;

import java.util.List;

public interface Store {
    void init();
    Item add(Item item);
    boolean replace(String id, Item item);
    boolean delete(String id);
    List<Item> findAll();
    List<Item> findName(String key);
    Item findById(String id);
}
