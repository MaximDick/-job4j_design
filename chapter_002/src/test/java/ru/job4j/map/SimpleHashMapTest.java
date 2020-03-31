package ru.job4j.map;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {
    @Test
    public void whenInsertedThenGetReturnsCorrectUser() {
        SimpleHashMap<Integer, User> map = new SimpleHashMap<>();
        Calendar currentTime = Calendar.getInstance();
        map.insert(0, new User("Ivan", 2, currentTime));
        map.insert(1, new User("Katya", 3, currentTime));
        map.insert(2, new User("Oleg", 1, currentTime));
        assertThat(map.get(0).getName(), is("Ivan"));
        assertThat(map.get(1).getChildren(), is(3));
        assertEquals(map.get(2).getBirthday().getTimeInMillis(), currentTime.getTimeInMillis());
    }

    @Test
    public void whenInsertingByDuplicateKeyShouldReplacesValueAndReturnsTrue() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "Dima");
        map.insert(1, "Anna");
        assertThat(map.insert(0, "Leo"), is(true));
        assertThat(map.insert(0, "Jay-Z"), is(true));
        assertThat(map.get(0), is("Jay-Z"));
    }

    @Test
    public void whenInvocationNullKeyThenResultsAreCorrect() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "Bob");
        map.insert(1, "Ann");
        assertNull(map.get(null));
        map = new SimpleHashMap<>();
        assertTrue(map.insert(null, "Olga"));
        assertThat(map.getSize(), is(1));
        assertEquals(map.get(null), "Olga");
    }

    @Test
    public void whenDeleteElementThenGetReturnsNull() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "Gino");
        map.insert(1, "Anna");
        map.insert(2, "Niki");
        assertTrue(map.delete(2));
        assertNull(map.get(2));
        assertTrue(map.insert(4, "Buy"));
        assertTrue(map.delete(4));
        assertNull(map.get(4));
    }

    @Test
    public void whenLoadDefaultIsReachedThenCapacityDoubles() {
        SimpleHashMap<Integer, User> newMap = new SimpleHashMap<>(4);
        Calendar birthday = new GregorianCalendar(2018, 11, 28);
        assertThat(newMap.size(), is(4));
        assertThat(newMap.getSize(), is(0));
        newMap.insert(3, new User("John2", 1, birthday));
        newMap.insert(4, new User("Tom3", 1, birthday));
        newMap.insert(5, new User("Niki4", 1, birthday));
        assertThat(newMap.size(), is(8));
        assertThat(newMap.getSize(), is(3));
    }

    @Test
    public void iteratorHasNextBeforeAndAfterInvocation() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(5, "Bob");
        map.insert(10, "Ann");
        map.insert(2, "Nik");
        Iterator iterator = map.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        System.out.println(map.entrySet()[0]);
        assertThat(iterator.next(), is(map.entrySet()[2]));
        assertThat(iterator.next(), is(map.entrySet()[5]));
        iterator.hasNext();
        assertThat(iterator.next(), is(map.entrySet()[10]));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void invocationNextWhenHasNotNextThrowsNSEE() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "Bob");
        map.insert(1, "Ann");
        map.insert(2, "Nik");
        Iterator<String> iterator = map.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void invocationNextAfterChangingInnerStateThrowsCME() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "Bob");
        map.insert(1, "Ann");
        map.insert(2, "Nik");
        Iterator<String> it = map.iterator();
        map.delete(0);
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void invocationNextAfterInsertingByDuplicateKeyShouldThrowsCME() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "Bob");
        map.insert(1, "Ann");
        map.insert(2, "Nik");
        Iterator<String> it = map.iterator();
        map.insert(1, "Ju");
        it.next();
    }

    @Test
    public void whenInsertingUnconsecutiveKeysThenSecondCallNextShouldReturnExpectedValue() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "Bob");
        map.insert(10, "Ann");
        Iterator iterator = map.iterator();
        assertThat(iterator.next(), is(map.entrySet()[1]));
        assertThat(iterator.next(), is(map.entrySet()[10]));
        assertThat(iterator.hasNext(), is(false));
    }

}