package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] array;
    private int position;

    public SimpleArray(int size) {
        this.array = new Object[size];
        this.position = 0;
    }

    public SimpleArray() {
        array = new Object[10];
    }

    public void checkIndexPosition(int position) {
        if (array.length < position) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void add(T model) {
        if (this.position >= array.length) {
            throw new NullPointerException("The array is full.");
        }
        this.array[position++] = model;
    }

    public void set(int position, T model) {
        checkIndexPosition(position);
        this.array[position] = model;
    }

    public void remove(int index) {
        if (index > position) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.array[position--] = null;
        System.arraycopy(array, position + 1, array, position, array.length - position - 1);
    }

    public T get(int index) {
        if (index >= position) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) this.array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < position;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[index++];
            }
        };
    }
}
