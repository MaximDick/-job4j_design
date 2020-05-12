package ru.job4j.db.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dumb) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines()
                    .map(line -> line.split(";"))
                    .forEach(array -> users.add(new User(array[0], array[1])));
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(1, user.email);
                    ps.execute();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static class User {

        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("./spam.properties")) {
            cfg.load(in);
        }

        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }
}


