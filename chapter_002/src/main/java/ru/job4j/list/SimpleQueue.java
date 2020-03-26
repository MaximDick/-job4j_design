package ru.job4j.list;

/**
 * task 5.3.3.1 Очередь на двух стеках[#241573]*/
public class SimpleQueue<T> {
    private SimpleLinkedList<T> linked = new SimpleLinkedList<>();

    public void push(T value) {
        linked.add(value);
    }

    public T poll() {
        return  linked.deleteFirst();
    }
}
