package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * 1. Читаем файл конфигурации[#241607]*/
public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    private static final String FLAG = "=";

    public void load() {
        this.values.clear();
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(
                    line -> {
                        if (line.contains(FLAG)) {
                            int position = line.indexOf(FLAG);
                            values.put(line.substring(0, position), line.substring(position + 1));
                        } else {
                            values.put(line, "");
                        }

                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String value(String key) {
        if (values.isEmpty()) {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
        return values.get(key);
    }


    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
