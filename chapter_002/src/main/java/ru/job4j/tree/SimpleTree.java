package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * task 1. Создать элементарную структуру дерева[#241600]
 */

public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {

    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);



//    class Node<E> {
//
//    }
}
