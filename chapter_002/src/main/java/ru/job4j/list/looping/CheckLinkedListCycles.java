package ru.job4j.list.looping;

/** task 5.3.4. Задан связанный список. Определить цикличность.[#241574].*/
public class CheckLinkedListCycles {

    /**
     * @param first первый узел списка.
     * @return true - если список содержит замыкание, иначе false.
     */
    public <T> boolean hasCycle(Node<T> first) {

        boolean result = false;
        if (first != null) {

            Node<T> slow = first; // создание ссылок
            Node<T> fast = first;

            while (!result && slow.getNext() != null
                    && fast.getNext() != null && fast.getNext().getNext() != null) {

                slow = slow.getNext(); //1 step

                fast = fast.getNext().getNext(); //2 steps


                if (slow.equals(fast)) {
                    return true; // если встретились. то имеем петлю
                }
            }
        }
        return result;
    }
}