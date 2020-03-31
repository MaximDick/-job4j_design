package ru.job4j.set;

import ru.job4j.list.DynamicListArrayBased;

import java.util.Iterator;

/** task 1.Реализовать коллекцию Set на массиве[#241586]
 * */
public class SimpleSet<E> implements Iterable<E> {

    private DynamicListArrayBased<E> store;

    public SimpleSet() {
        this.store = new DynamicListArrayBased<>();
    }

    /**
     * Конструктор инициализирует хранилище заданным размером.
     * */
    public SimpleSet(int size) {
        this.store = new DynamicListArrayBased<>(size);
    }

    /**
     * Добавляет элемент в хранилище.
     * Проверяет чтоб хранились уникальные значения.
     * @param value  добавляемый элемент в хранилище.
     * @return true - если добавление успешно,
     *         false - добавление не произошло.
     **/
    public boolean add(E value) {
        if (!contains(value)) {
            this.store.add(value);
            return true;
        }
        return false;
    }

    /**
     * Проверяет наличие заданного значения в массиве.
     *
     * @param value искомое значение.
     * @retur result true - если присутствует,
     *               false - если такого значения нет.
     **/
    public boolean contains(E value) {
        boolean result = false;
        for (E elem : this.store) {
            if (elem.equals(value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return this.store.iterator();
    }



}
