package ru.job4j.ttd;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Maxim Dick (maxim1994barca@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MaxMinTest {

    @Test
    public void whenMaxNameCar() {
        List<Car> list = Arrays.asList(
                new Car("bmw", "black"),
                new Car("mercedes", "white"),
                new Car("toyota", "yellow")
        );
        MaxMin maxMini = new MaxMin();
        Car result = maxMini.max(list, Comparator.comparingInt(left -> left.getName().length()));

        Car expected = new Car("mercedes", "white");

        assertEquals(result.getName(), expected.getName());
    }

    @Test
    public void whenMinNameCar() {
        List<Car> list = Arrays.asList(
                new Car("bmw", "black"),
                new Car("mercedes", "white"),
                new Car("toyota", "yellow")
        );
        MaxMin maxMini = new MaxMin();
        Car result = maxMini.min(list, Comparator.comparingInt(left -> left.getName().length()));

        Car expected = new Car("bmw", "black");

        assertEquals(result.getName(), expected.getName());
    }

}

