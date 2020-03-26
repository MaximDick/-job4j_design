package ru.job4j.iterator;

import java.util.Iterator;

/**
 * task 5.1.1. Итератор для двухмерного массива int[][][#241580].
 * */
public class MatrixIterator<T> implements Iterator<T> {

    private T[][] matrix;
    private int row = 0;       //строка текущего элемента
    private int col = 0;       //столбец текущего элемента


    protected MatrixIterator(T[][] matrix) {
        this.matrix = matrix;
    }


    @Override
    public boolean hasNext() {
        return this.row < this.matrix.length - 1 || this.col < matrix[row].length;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T element = matrix[row][col++];
        if (col >= matrix[row].length) {
            col = 0;
            row++;
        }
        return element;
    }
}
