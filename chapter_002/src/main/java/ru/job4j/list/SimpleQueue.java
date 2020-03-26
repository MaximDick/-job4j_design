package ru.job4j.list;

/**
 * task 5.3.3.1 Очередь на двух стеках[#241573]
 * */
public class SimpleQueue<T> {
    private SimpleStack<T> stack1;
    private SimpleStack<T> stack2;

    public SimpleQueue() {
        this.stack1 = new SimpleStack<>();
        this.stack2 = new SimpleStack<>();
    }

    public void push(T value) {
        stack1.push(value);
    }

    public T poll() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.poll());
            }
        }
        return stack2.isEmpty() ? null : stack2.poll();
    }
}
