package ru.job4j.db.sqltracker;

import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TrackerSQL implements Store, AutoCloseable {
    private  Connection cn;

    public TrackerSQL(Connection cn) {
        this.cn = cn;
    }

    public void init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            PreparedStatement st = cn.prepareStatement("CREATE TABLE IF NOT EXISTS items (id serial primary key,name varchar(50), description varchar(200))");
            st.executeUpdate();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement stat = cn.prepareStatement("INSERT INTO items (name, description) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                stat.setString(1, item.getName());
                stat.setString(2, item.getDescription());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        try (PreparedStatement stat = cn.prepareStatement("UPDATE items set name = ?, description = ? WHERE id = ?")) {
            stat.setString(1, item.getName());
            stat.setString(2, item.getDescription());
            stat.setInt(3, Integer.parseInt(id));
            if (stat.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        try (PreparedStatement stat = cn.prepareStatement("DELETE FROM items WHERE id = ?")) {
            stat.setInt(1, Integer.parseInt(id));
            if (stat.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try  (PreparedStatement stat = cn.prepareStatement("SELECT * FROM items ")) {
           try (ResultSet rsl = stat.executeQuery()) {
               while (rsl.next()) {
                   String name = rsl.getString(2);
                   String desc = rsl.getString(3);
                   items.add(new Item(name, desc));
               }
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement stat = cn.prepareStatement("SELECT * FROM items WHERE name = ?")) {
            stat.setString(1, key);
            try (ResultSet rsl = stat.executeQuery()) {
                while (rsl.next()) {
                    String name = rsl.getString(2);
                    String desc = rsl.getString(3);
                    items.add(new Item(name, desc));
                }
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item findById(String id) {
        try (PreparedStatement stat = cn.prepareStatement("SELECT * FROM items WHERE id = ?")) {
            stat.setInt(1, Integer.parseInt(id));
            try (ResultSet rsl = stat.executeQuery()) {
                while (rsl.next()) {
                    String name = rsl.getString(2);
                    String desc = rsl.getString(3);
                    return  new Item(name, desc);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
