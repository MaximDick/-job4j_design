package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void ifAddNotEqualElementsThenSetReturnThisElements() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> testIterator = set.iterator();
        assertThat(testIterator.next(), is(1));
        assertThat(testIterator.next(), is(2));
    }

    @Test
    public void ifAddEqualsElementsThenSetReturnNotEqualsElements() {
        SimpleSet<Integer> set = new SimpleSet<>();
        boolean firstRsl = set.add(1);
        boolean secondRsl = set.add(1);
        set.add(1);
        set.add(2);
        Iterator<Integer> testIterator = set.iterator();
        assertThat(firstRsl, is(true));
        assertThat(secondRsl, is(false));
        assertThat(testIterator.next(), is(1));
        assertThat(testIterator.next(), is(2));
    }

    @Test
    public void ifAllElementsArePassedIteratorHasNextWasFalse() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        Iterator<Integer> testIterator = set.iterator();
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(1));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(2));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(3));
        assertThat(testIterator.hasNext(), is(false));
    }



}