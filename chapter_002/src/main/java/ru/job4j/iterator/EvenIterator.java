package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator<Integer> implements Iterator {

    private int index = -1;
    private final int[] arr;

    public EvenIterator(int[] arr) {
        this.arr = arr;
    }

    private int nextEvenIndex() {
        int next = index + 1;
        while (next < arr.length && arr[next] % 2 != 0) {
            next++;
        }
        return next;
    }

    @Override
    public boolean hasNext() {
        return nextEvenIndex() < arr.length;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Array passed");
        }
        index = nextEvenIndex();
        return arr[index];
    }
}
