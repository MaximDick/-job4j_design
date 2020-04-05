package ru.job4j.tree;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TreeTest {

    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test(expected = ConcurrentModificationException.class)
        public void whenNewElAddedNextInvocationShouldThrowCMW() {
            Tree<Integer> tree = new Tree<>(1);
            tree.add(1, 2);
            tree.add(1, 3);
            Iterator<Integer> iterator = tree.iterator();
            tree.add(3, 5);
            iterator.next();
        }

        @Test(expected = NoSuchElementException.class)
    public void whenNewElAddedHasNextInvocationShouldThrowCMW() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 5);
        Iterator iterator = tree.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }

    @Test
    public void whenChildrenAmountTwoOrLessThenIsBinaryReturnsTrue() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 5);
        assertThat(tree.isBinary(), is(true));
    }

    @Test
    public void whenThreeChildrenThenIsBinaryReturnsFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 5);
        tree.add(3, 6);
        tree.add(3, 7);
        assertThat(tree.isBinary(), is(false));
    }
}
