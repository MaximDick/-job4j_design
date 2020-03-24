package ru.job4j.generic;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AbstractStoreTest {


    @Test
    public void add() {
        SimpleArray<Integer> array = new SimpleArray<>(5);
        array.add(1);
        assertThat(array.get(0), is(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void set() {
        SimpleArray<Integer> array = new SimpleArray<>(5);
        array.add(1);
        assertThat(array.get(0), is(1));
        array.set(0, 2);
        assertThat(array.get(0), is(2));
        array.set(1, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void remove() {
        SimpleArray<Integer> array = new SimpleArray<>(5);
        array.add(0);
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.remove(1);
        array.remove(1);
        array.remove(1);
        array.add(9);
        assertThat(array.get(0), is(0));
        assertThat(array.get(1), is(4));
        assertThat(array.get(2), is(9));
        array.get(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get() {
        SimpleArray<Integer> array = new SimpleArray<>(5);
        array.add(1);
        array.add(2);
        array.add(3);
        assertThat(array.get(0), is(1));
        assertThat(array.get(1), is(2));
        assertThat(array.get(2), is(3));
        array.get(3);
    }

    @Test
    public void iterator() {
        SimpleArray<Integer> array = new SimpleArray<>(5);
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(5);
        Iterator<Integer> it = array.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNoSuchElementException() {
        SimpleArray<Integer> array = new SimpleArray<>(5);
        array.add(1);
        array.add(2);
        array.add(3);
        Iterator<Integer> it = array.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

}