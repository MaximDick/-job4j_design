package ru.job4j.map;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * task 8. Реализовать собственную структуру данных - HashMap[#241596]
 * */
public class SimpleHashMap<K, V> implements Iterable<V> {

    /**
     * Вместимость массива поумолчанию 16.
     * */
    private static final int DEFAULT_CAPACITY = 16;

    /**
     * Коэффициент загрузки, при достижении размера массива удваивается.
     * */
    private static final float LOAD_FACTOR = 0.75f;

    /**Массив для храниния пары Key-Value.
     * */
    private Node<K, V>[] table;

    /**
     * количество добавленных элементов в массиве.
     * */
    private int size;

    /**Счетчик для структурных изменений (для реализации fail-fast поведения итератора).
     * */
    private int modCount = 0;

    /**
     * Конструктор с пустым хранилищем с начальной вместимостью заданной поумолчанию.
     * */
    public  SimpleHashMap() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * @param initialCapacity - начальная вместимость.
     * @throws IllegalArgumentException - не корректное значение вместимости.
     * */
    public SimpleHashMap(int initialCapacity) {
        if (initialCapacity < 0 && initialCapacity % 2 != 0) {
            throw new IllegalArgumentException();
        }
        this.resize(initialCapacity);
    }

    /**
     * Хэш функция на основе хэш-кода ключа.
     * Сдвигаем старшие разряды числа(начального хеш-кода ключа) вправо на 16 позиций (>>> 16)
     *      * и выполняем операцию XOR (^ побитовое логическое или).
     *      * Этим страхуемся от неудачной функции hashcode().
     * */
    private int hash(K key) {
        int hash = 0;
        if (key != null) {
            hash = key.hashCode();
            hash = hash ^ (hash >>> 16);
        }
        return hash;
    }


    /**
     * Вычисляет корзину/индекс ячейки/бакет в массиве, в которой будет храниться новый элемент.
     * @param hash результат хеш-функции для нового элемента.
     * @param length количество ячеек/размер массива.
     * @return индекс ячейки.
     * */
    private int index(int hash, int length) {
        return hash & (length - 1);
    }

    /**
     * Добавляет в карту новый объект на основе заданноой пары ключ-значение.
     * this.table.length << 1 удвоение размера массива. Побитовый сдвиг влево.
     * @param key - ключ.
     * @param value - значение.
     * @return result.
     * */
    public boolean insert(K key, V value) {
        boolean result = false;
        int i = this.index(this.hash(key), this.table.length);
        if (this.table[i] == null) {
            this.table[i] = new Node<>(key, value);
            this.size++;
            this.modCount++;
            result = true;
            if (this.size >= LOAD_FACTOR * this.table.length) {
                resize(this.table.length << 1);
            }
        } else {
            if (key != null && key.equals(this.table[i].key)) {
                this.table[i] = new Node<>(key, value);
                this.modCount++;
                result = true;
            }
        }
        return result;
    }

    /**
     * Создает новое хранилище заданной вместимости.
     * Перемещает в него элементы из текущего, если они не ull.
     *
     * @param newSize - заданная вместимость.
     * @return карта новой вместимости.
     * */
    private void resize(int newSize) {
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[newSize];
        if (this.table != null) {
            for (Node<K, V> node : this.table) {
                if (node != null) {
                    int i = this.index(this.hash(node.key), newSize);
                    newTable[i] = node;
                }
            }
        }
        this.table = newTable;
    }

    /**
     * Возвращает значение по хаданному ключу.
     * @param key
     * @return value.
     * */
    public V get(K key) {
        int i = this.index(this.hash(key), this.table.length);
        Node<K, V> node = this.table[i];
        return node != null && Objects.equals(key, node.key) ? node.value : null;
    }

    /**
     * Удаляет из карты объект по заданному ключу.
     *@param key ключ заданный для удаления пары.
     * @return result.
     * */
    public boolean delete(K key) {
        boolean result = false;
        int i = this.index(this.hash(key), this.table.length);
        Node<K, V> node = this.table[i];
        if (node != null && key.equals(node.key)) {
            this.table[i] = null;
            this.modCount++;
            result = true;
        }
        return result;
    }

    /**
     * Вспомогательный метод, получает полный размер карты (с учетом пустых ячеек).
     *
     * @return размер карты (с учетом пустых ячеек).
     */
    public int size() {
        return this.table.length;
    }

    /**
     * Вспомогательный метод, получает размер заполненной части карты.
     * */
    public int getSize() {
        return this.size;
    }


    /**
     * Вспомогательный метод, получает множество пар ключ-значение.
     *
     * @return множество пар ключ-значение, в котором могут быть значения nell.
     */
    public Node<K, V>[] entrySet() {
        return this.table;
    }

    /**
     * Возвращает итератор для последовательного прохода по элементам карты.
     * @return переопределенный итератор.
     */
    @Override
    public Iterator iterator() {
        return new Iterator() {
            int expectedModCount = SimpleHashMap.this.modCount;
            private int pointer;
            private int counter;

            @Override
            public boolean hasNext() {
                for (int i = pointer; i < table.length; i++) {
                    if (table[i] != null) {
                        pointer = i;
                        break;
                    }
                }
                return counter < SimpleHashMap.this.size;
            }

            @Override
            public Node<K, V> next() {
                if (this.expectedModCount != SimpleHashMap.this.modCount) {
                    throw  new ConcurrentModificationException();
                }
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                counter++;
                return table[pointer++];
            }
        };
    }

    /**Внутренний класс для хранения в виде пар ключ-значение.
     * */
    private static class Node<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Node{" + "key=" + key + ", value=" + value + '}';
        }
    }





}

