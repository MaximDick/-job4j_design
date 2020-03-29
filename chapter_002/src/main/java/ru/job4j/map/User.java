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

    public Calendar getBirthday() {
        return birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return children == user.children
                && (name == user.name
                || (name != null && name.equals(user.getName()))) && (birthday == user.birthday
                || (birthday != null && birthday .equals(user.getBirthday())
        ));
    }

//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + children;
//        result = prime * result + ((name == null) ? 0 : name.hashCode());
//        result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
//        return result;
//    }
}


