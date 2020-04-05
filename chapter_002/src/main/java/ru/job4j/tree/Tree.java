package ru.job4j.tree;

import java.util.*;

 class Tree<E> implements SimpleTree<E> {
    /**
     * Корневой элемент дерева.
     * */
    private final Node<E> root;

    /**
     * Конструктор формирует дерево с указанным корневым элементов.
     * @param root корневой элемент.
     * */
    Tree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * @param parent - корень к которому добавляем.
     * @param child добавляемый элемент.
     * @return true - если добавление прошло успешно.
     *         false - если не удалось добавить элемент.
     * */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> node = findBy(parent);
        if (node.isPresent() && findBy(child).isEmpty()) {
            node.get().children.add(new Node<>(child));
            rsl = true;
        }
        return rsl;
    }

    /**
     * Поиск заданного элемента.
     * @param value заданный элемент.
     * @return узел заданного элемента.
     * */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer((this.root));
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
             data.addAll(el.children);
        }
        return rsl;
    }

}
