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
     * @param element  добавляемый элемент в хранилище.
     * @return true - если добавление успешно,
     *         false - добавление не произошло.
     **/
    public boolean add(E element) {
        for (E elem : this.store) {
            if (elem.equals(element)) {
                return false;
            }
        }
        this.store.add(element);
        return true;
    }


    @Override
    public Iterator<E> iterator() {
        return this.store.iterator();
    }



}
