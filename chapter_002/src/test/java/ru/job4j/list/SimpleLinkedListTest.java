package ru.job4j.list;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleLinkedListTest {

    @Test
    public void addAndGetElementsFromSimpleLinkedList() {
        SimpleList<Integer> list = new SimpleLinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
        assertThat(list.get(3), is(5));
    }

    @Test
    public void shouldReturnAllNumbersSequentially() {
        SimpleList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void isHasNoNextElementsShouldReturnNoSuchElementException() {
        SimpleList<Integer> list = new SimpleLinkedList<>();
        list.add(22);
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(22));
        iterator.next();
    }


}