package ru.job4j.list;

/**
 * task 5.3.3. Используя контейнер на базе
 * связанного списка создать контейнер Stack[#241570]*/
public class SimpleStack<T> {
    private SimpleLinkedList<T> linked;

    public SimpleStack() {
        linked = new SimpleLinkedList<>();
    }

    public T poll() {
       return linked.deleteLast();
    }

    protected boolean isEmpty() {
        return linked.getSize() == 0;
    }

    public void push(T value) {
        linked.add(value);
    }
}
