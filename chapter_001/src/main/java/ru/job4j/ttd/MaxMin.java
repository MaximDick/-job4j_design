package ru.job4j.ttd;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * @author Maxim Dick (maxim1994barca@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MaxMin {

    private <T> T find(List<T> values, BiPredicate<T, T> select) {
        T result = null;
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("empty list");
        }
        for (int idx = 0; idx < values.size() - 1; idx++) {
            if (select.test(values.get(idx), values.get(idx + 1))) {
                result = values.get(idx);
            }
        }
        return result;
    }

    public <T> T max(List<T> values, Comparator<T> comp) {
        return find(values, (x, y) -> comp.compare(x, y) > 0);
    }

    public <T> T min(List<T> values, Comparator<T> comp) {
        return find(values, (x, y) -> comp.compare(x, y) < 0);
    }

}
