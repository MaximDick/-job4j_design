package ru.job4j.map;

import java.util.Calendar;

/**task 1. Создать модель User[#241589].*/
public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }
}
