package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * Корневой элемент дерева.
     * */
    private final Node<E> root;

    /**
     * Счётчик структурных изменений (для реализации fail-fast поведения итератора).
     */
    private int modCount;
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
//        boolean rsl = false;
//        Optional<Node<E>> node = findBy(parent);
//        if (node.isPresent() && findBy(child).isEmpty()) {
//            node.get().children.add(new Node<>(child));
//            rsl = true;
        boolean rsl = false;
        Optional<Node<E>> checkParent = findBy(parent);
        Optional<Node<E>> checkChild = findBy(child);
        if (!checkChild.isPresent()) {
            checkParent.ifPresent(node -> node.add(new Node<>(child)));
            rsl = true;
            this.modCount++;
        }
        return rsl;
    }

    /**
     * Поиск заданного элемента.
     * @param value заданный элемент.
     * @return узел заданного элемента.
     * */
//    @Override
//    public Optional<Node<E>> findBy(E value) {
//        Optional<Node<E>> rsl = Optional.empty();
//        Queue<Node<E>> data = new LinkedList<>();
//        data.offer((this.root));
//        while (!data.isEmpty()) {
//            Node<E> el = data.poll();
//            if (el.value.equals(value)) {
//                rsl = Optional.of(el);
//                break;
//            }
//             data.addAll(el.children);
//        }
//        return rsl;
//    }
    /**
     * Осуществляет поиск в дереве узла с заданным ключем.
     * @param value заданное значение/ключ.
     * @return контейнер Optional(содержит искомый элемент при его наличии).
     * */
    @Override
    public Optional<Node<E>> findBy(E value) {
        return this.findNode(node -> node.eqValues(value));
    }

    /**
     * находит узел в дереве по заданному условию.
     * */
    public Optional<Node<E>> findNode(Predicate<Node<E>> predicate) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer((this.root));
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (predicate.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Проверяет колиество дочерних элементов в каждом узле дерева.
     * Если во всех узлах <= 2 - то дерево бинарное.
     * */
    public boolean isBinary() {
        return !this.findNode(node -> node.leaves().size() > 2).isPresent();
    }


    /**
     * Возвращает итератор для последовательного прохода в ширину по элементам дерева.
     *
     * @return переопределенный итератор.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Queue<Node<E>> data = new LinkedList<>((List.of(Tree.this.root)));
            private int expectedModCount = Tree.this.modCount;

            @Override
            public boolean hasNext() {
                return !this.data.isEmpty();
            }

            @Override
            public E next() {
                if (this.expectedModCount != Tree.this.modCount) {
                    throw  new ConcurrentModificationException();
                }

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> node = this.data.remove();
                this.data.addAll(node.leaves());
                return node.getValue();
            }
        };
    }
}
