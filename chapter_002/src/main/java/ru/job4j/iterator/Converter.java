package ru.job4j.iterator;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
       return new Iterator<>() {
           Iterator<Integer> iterator = it.next();

        @Override
        public boolean hasNext() {
            while (it.hasNext() && !iterator.hasNext()) {
                iterator = it.next();
            }
            return iterator.hasNext();
        }

        @Override
        public Integer next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("No elements");
            }
            return iterator.next();
        }
    };
    }
}
