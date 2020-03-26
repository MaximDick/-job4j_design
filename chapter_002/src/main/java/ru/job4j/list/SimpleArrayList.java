package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * task 5.3.0 Создать метод delete для односвязного списка[#241569].
 * */
public class SimpleArrayList<E> {
    private int size;
    private Node<E> first;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E data) {
        Node<E> newLink = new Node(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Проверка на пустоту
     * */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Реализовать метод удаления первого элемент в списке.
     */
    public E delete() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node<E> firstOne = first;

        // Сохраняем возвращаемый результат
        E result = firstOne.data;
        // Сохраняем ссылку на след. Node
        Node<E> nextLink = firstOne.next;

        // Переназначаем first, След. Node
        first = nextLink;

        this.size--;
        return result;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
        }
    }

}
