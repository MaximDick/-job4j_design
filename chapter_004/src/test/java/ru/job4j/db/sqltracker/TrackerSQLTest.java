package ru.job4j.db.sqltracker;

import org.junit.Test;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class TrackerSQLTest {
    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name", "desc"));
            assertThat(tracker.findByName("name").size(), is(1));
        }
    }

    @Test
    public void checkAdd() throws Exception {
        Item car = new Item("BMW", "M5 f10");
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item expected = trackerSQL.add(new Item("BMW", "M5 f10"));
            car.setId(trackerSQL.findByName("BMW").get(0).getId());
            assertEquals(expected, car);
        }
    }

    @Test
    public void checkReplace() throws Exception {
        Item lada = new Item("lada", "granta");
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            trackerSQL.add(new Item("uaz", "hunter"));
            boolean expected = trackerSQL.replace(trackerSQL.findByName("uaz").get(0).getId(), lada);
            assertThat(expected, is(true));
        }
    }

    @Test
    public void checkDelete() throws Exception {
       try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
        trackerSQL.add(new Item("Barcelona", "Messi"));
        boolean expected = trackerSQL.delete(trackerSQL.findByName("Barcelona").get(0).getId());
        assertThat(expected, is(true));
        }
    }

    @Test
    public void checkFindById() throws Exception {
        Item mercedes = new Item("Mercedes", "E class");
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            trackerSQL.add(mercedes);
            String id = trackerSQL.findByName("Mercedes").get(0).getId();
            Item expected = trackerSQL.findById(id);
            mercedes.setId(id);
            assertEquals(expected, mercedes);
        }
    }

    @Test
    public void checkFindAll() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            trackerSQL.add(new Item("Fedorov", "Forward"));
            trackerSQL.add(new Item("Bure", "Forward"));
            trackerSQL.add(new Item("Malkin", "Forward"));
            List<Item> expected = trackerSQL.findAll();
            assertThat(expected.size(), is(3));
        }
    }

    @Test
    public void checkByName() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            trackerSQL.add(new Item("BMW", "X5M"));
            trackerSQL.add(new Item("BMW", "M5"));
            List<Item> expected = trackerSQL.findByName("BMW");
            assertThat(expected.size(), is(2));

        }
    }




}