package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicListArrayBased<E> implements SimpleList<E> {

    private Object[] array;
    private int index;

    /**
     * Количество раз, когда этот список был структурно изменен.
     */
    private int modCount = 0;
    /**
     * коэффициент заполнения*/
    private static final double FILL_FACTOR = 0.75D;

    /**
     * коэффициент расширения*/
    private static final double MAGNIFICATION_FACTOR = 1.5D;

    /**
     * Начальная вместимость
     * */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Конструктор инициализирует массив array,
     * заданным размером.
     * @param size - заданный размер.
     * */
    public DynamicListArrayBased(int size) {
        this.array = new Object[size];
    }

    /**
     * Конструктор инициализирует массив array, заданным размером
     * 10 - заданный размер.
     * */
    public DynamicListArrayBased() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Проверка на заполнение массива и
     * при необходимости увеличивать его.
     * */
    private void checkEnsureCapacity() {
        if (index > array.length) {
            this.array = Arrays.copyOf(this.array, (int) (this.index * MAGNIFICATION_FACTOR));
        }
    }

    /**
     * ДОбавление элемента.
     * @param value
     * */
    @Override
    public void add(E value) {
        checkEnsureCapacity();
        this.modCount++;
        this.array[index++] = value;
    }

    /**
     * Возвращает элемент из хранилища.
     * @param index - индекс соответствующего элемента.
     * @return возвращает элемент соответствующий index.
     */
    @Override
    public E get(int index) {
        return (E) this.array[index];
    }

    /**
     *@return Итератор для обхода структуры.
     * */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int position;
            private final int expectedModCount = modCount;

            /**
             * Проверяет есть ли следующий элемент, и не достигнут ли конец.
             * @return true - если элемент есть, false - конец коллекции.
             */
            @Override
            public boolean hasNext() {
                return (this.position < index);
            }

            /**
             * Возвращает текущий элемент и переводит указатель.
             * @return текущий элемент.
             * */
            public E next() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (this.position >= index) {
                    throw new NoSuchElementException();
                }
                return (E) array[position++];
            }
        };
    }


}
