package ru.job4j.ttd;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Maxim Dick (maxim1994barca@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return Collections.max(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return Collections.min(value, comparator);
    }
}