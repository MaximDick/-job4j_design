package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DynamicListArrayBasedTest {

    private SimpleList<Integer> list;

    @Before
    public void beforeTest() {
        list = new DynamicListArrayBased<>();
        list.add(10);
        list.add(5);
        list.add(22);
    }

    /**
     * Add element.
     * */
    @Test
    public void ifAddIntegerToTestListThenHasThisInteger() {
        assertThat(list.get(0), is(10));
        assertThat(list.get(1), is(5));
        assertThat(list.get(2), is(22));
    }

    /**
     * Iterator
     * */
    @Test
    public void hasNextNextSequentialInvocation() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(10));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(22));
        assertThat(iterator.hasNext(), is(false));
    }

    /**
     * ConcurrentModificationException in iterator.
     * */
    @Test(expected = ConcurrentModificationException.class)
    public void ifCollectionWasModificationThenThrowConcurrentModificationException() {
        SimpleList list = new DynamicListArrayBased();
        Iterator<Integer> iterator = list.iterator();
        list.add(22);
        iterator.next();
    }

}