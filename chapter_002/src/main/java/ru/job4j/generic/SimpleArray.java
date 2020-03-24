package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {

    private T[] array;
    private int size;

//   public SimpleArray(T[] model) {
//       this.array = (T[]) new Object[size];
//   }

    public SimpleArray(int size) {
        this.array = (T[]) new Object[size];
        this.size = 0;
    }

    public SimpleArray() {
        array = (T[]) new Object[10];
    }

    public void checkIndexPosition(int indx) {
        if (size == 0 || indx < 0 || indx >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void add(T model) {
        if (this.size >= array.length) {
            throw new NullPointerException("The array is full.");
        }
        this.array[size++] = model;
    }

    public void set(int index, T model) {
//        if (size == 0 || index < 0 || index >= size) {
//            throw new ArrayIndexOutOfBoundsException();
//        }
        checkIndexPosition(index);
        this.array[index] = model;
    }

    public void remove(int index) {
//        if (size == 0 || index < 0 || index >= size) {
//            throw new ArrayIndexOutOfBoundsException();
//        }
        checkIndexPosition(index);
        this.array[size--] = null;
        System.arraycopy(array, size + 1, array, size, array.length - size - 1);
    }

    public T get(int index) {
//        if (size == 0 || index < 0 || index >= size) {
//            throw new ArrayIndexOutOfBoundsException();
//        }
        checkIndexPosition(index);
        return this.array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[pos++];
            }
        };
    }
}
