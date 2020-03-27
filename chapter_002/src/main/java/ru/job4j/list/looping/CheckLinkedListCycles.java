package ru.job4j.list.looping;

/** task 5.3.4. Задан связанный список. Определить цикличность.[#241574].*/
public class CheckLinkedListCycles {

    /**
     * @param first первый узел списка.
     * @return true - если список содержит замыкание, иначе false. */
    public <T> boolean hasCycle(Node<T> first) {

        if (first == null) {
            return false; // список не существует, то и петли не будет
        }

        Node<T> slow, fast; // создание ссылок
        slow = first;
        fast = first;

        while (true) {
            slow = slow.next; //1 step

            if (fast.next != null) {
                fast = fast.next.next; //2 steps
            } else {
                return false;
            }

            if (slow == null || fast == null) {
                return false; // если хотя бы один == null, то нет петли
            }

            if (slow == fast) {
                return true; // если встретились. то имеем петлю
            }
        }
    }
}
