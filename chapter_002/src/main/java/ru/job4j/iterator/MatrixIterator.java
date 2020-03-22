package ru.job4j.iterator;

import java.util.Iterator;

public class MatrixIterator<T> implements Iterator<T> {

    private int size;          //кол-во элементов в матрице
    private int position = 0;  //номер текущего элемента для "выдачи"
    private int row = 0;       //строка текущего элемента
    private int col = 0;       //столбец текущего элемента
    private T[][] matrix;

    public MatrixIterator(T[][] matrix) {
        this.matrix = matrix;
        this.size = countElements(matrix);
    }

    private int countElements(T[][] matrix) {  //считаем количество элементов в матрице
        int count = 0;
        for (T[] row : matrix) {
            count += row.length;
        }
        return count;
    }

    @Override
    public boolean hasNext() {
        return position < size;
    }

    @Override
    public T next() {

//      переходим к следующему элементу
        position++;

        T element = matrix[row][col++];
        if (col >= matrix[row].length) {
            col = 0;
            row++;
        }
        return element;
    }
}
