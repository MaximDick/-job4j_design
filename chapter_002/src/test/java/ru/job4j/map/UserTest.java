package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


/**
 * task 2. Не перекрывать equals hashCode[#241593].
 * */
public class UserTest {

    /**По бизнес логике это два одинаковых объекта, но так как
     * методы equals и hashcode не переопределены, то и хешкод у них будут разные, следовательно это два разных обекта.
     * Распечатает {ru.job4j.map.User@721e0f4f=Vladimir, ru.job4j.map.User@28864e92=Vladimir}.
     * */
    @Test
    public void whenCreationOfTwoUsersAndOutputInDisplay() {
        User user1 = new User("Vladimir", 1, new GregorianCalendar(1994, 1, 27));
        User user2 = new User("Vladimir", 1, new GregorianCalendar(1994, 1, 27));
        Map<User, Object> map = new HashMap<>();
        map.put(user1, user1.getName());
        map.put(user2, user2.getName());
        System.out.println(map);
    }

    /**
     * Так как hashcode() тоже не переопределен, то каждый раз при создании будем получать разные хеш-коды.
     *
     * Так как метод equals не переопределен,
     * то сравнение будет происходит только по ссылке,
     * а не по содержимому, поэтому выдаст, что объекты не равны.
     *
     * Но, как мы помним, должно выполняться правило:
     * “если у двух объектов одного и того же класса содержимое одинаковое,
     * то и хеш-коды должны быть одинаковые ”. Поэтому, при создании пользовательского класса,
     * принято переопределять методы hashCode() и equals() таким образом, что бы учитывались поля объекта.
     *
     * 1. Для одного и того-же объекта, хеш-код всегда будет одинаковым;
     * 2. Если объекты одинаковые, то и хеш-коды одинаковые (но не наоборот).
     * 3. Если хеш-коды равны, то входные объекты не всегда равны (коллизия);
     * 4.если хеш-коды разные, то и объекты гарантированно разные.
     *
     * */
    @Test
    public void whenUsersAreNotEqualByTheStandardMethod() {
        User user1 = new User("Vladimir", 1, new GregorianCalendar(1994, 1, 27));
        User user2 = new User("Vladimir", 1, new GregorianCalendar(1994, 1, 27));
        Map<User, Object> map = new HashMap<>();
        map.put(user1, user1.getName());
        map.put(user2, user2.getName());
        assertThat(user1.equals(user2), is(false));
    }

}