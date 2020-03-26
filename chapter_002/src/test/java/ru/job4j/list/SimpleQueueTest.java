package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleQueueTest {

    @Test
    public void whenPushThenPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        assertThat(queue.poll(), is(1));
    }

    @Test
    public void whenPushPollThenPushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.poll();
        queue.push(2);
        assertThat(queue.poll(), is(2));
    }

    @Test
    public void whenPushPushPushThenPollPollPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);

        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
    }

}