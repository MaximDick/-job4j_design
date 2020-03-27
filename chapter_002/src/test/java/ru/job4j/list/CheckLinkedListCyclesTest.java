package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.list.looping.CheckLinkedListCycles;
import ru.job4j.list.looping.Node;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CheckLinkedListCyclesTest {


    private Node<Integer> first;
    private Node<Integer> second;
    private Node<Integer> third;
    private Node<Integer> fouth;

    @Before
    public void setUp() {
        first = new Node<>(1);
        second = new Node<>(2);
        third = new Node<>(3);
        fouth = new Node<>(4);
    }

    /**
     * Список содержит замыкание.
     * */
    @Test
    public void isListHasCycle() {
        first.setNext(second);
        second.setNext(third);
        third.setNext(fouth);
        fouth.setNext(first);
        assertThat(new CheckLinkedListCycles().hasCycle(first), is(true));
    }

    /**
     * Нет замыкания.
     * */
    @Test
    public void isListHasNotCycle() {
        first.setNext(second);
        second.setNext(third);
        third.setNext(fouth);
        fouth.setNext(null);
        assertThat(new CheckLinkedListCycles().hasCycle(first), is(false));
    }

    /**
     * Замыкание на середине.
     * */
    @Test
    public void ifLastHasMidCycle() {
        first.setNext(second);
        second.setNext(third);
        third.setNext(second);
        fouth.setNext(null);
        assertThat(new CheckLinkedListCycles().hasCycle(first), is(true));
    }
}