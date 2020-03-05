package ru.job4j.ttd;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntPredicate;

/**
 * @author Maxim Dick (maxim1994barca@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MaxMin {

//    public <T> T max(List<T> value, Comparator<T> comparator) {
//        return Collections.max(value, comparator);
//    }
//
//    public <T> T min(List<T> value, Comparator<T> comparator) {
//        return Collections.min(value, comparator);
//    }

    public <T> T max(List<T> values, Comparator<T> comp) {
        return find(values, comp, x -> x < 0);
    }

    public <T> T min(List<T> values, Comparator<T> comp) {
        return find(values, comp, x -> x > 0);
    }

    private <T> T find(List<T> values, Comparator<T> comp, IntPredicate select) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("empty list");
        }
        T result = values.get(0);
        for (int idx = 1; idx < values.size(); idx++) {
            T next = values.get(idx);
            if (select.test(comp.compare(result, next))) {
                result = next;
            }
        }
        return result;
    }
}
