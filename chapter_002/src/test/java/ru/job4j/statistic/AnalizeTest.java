package ru.job4j.statistic;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizeTest {

    @Test
    public void whenAdded4ElAndChanged2ElAndDeleted1ElThenStatistics4And2And1() {
        Analize info = new Analize();
        List<Analize.User> previous = Arrays.asList(
                new Analize.User(1, "dog"),
                new Analize.User(2, "dog"),
                new Analize.User(3, "dog"),
                new Analize.User(4, "dog")
        );

        List<Analize.User> current = Arrays.asList(
                new Analize.User(1, "cat"),
                new Analize.User(2, "cat"),
                new Analize.User(3, "dog"),
                new Analize.User(5, "frog"),
                new Analize.User(6, "frog"),
                new Analize.User(8, "frog")
        );
        Analize.Info summary = info.diff(previous, current);
        System.out.println(summary);
        assertThat(summary.getChanged(), is(2));
        assertThat(summary.getDeleted(), is(1));
        assertThat(summary.getAdded(), is(3));
    }

}