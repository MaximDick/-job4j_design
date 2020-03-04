package ru.job4j.ttd;

import java.util.Map;

public interface Template {
    String generate(String template, Map<String, String> data);
}
