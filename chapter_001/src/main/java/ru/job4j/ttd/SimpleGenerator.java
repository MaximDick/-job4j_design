package ru.job4j.ttd;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleGenerator implements Template {

    /**
     * JAVADOC - https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html#compile(java.lang.String)
     * Compiles the given regular expression into a pattern with the given flags.
     * Компилирует данное регулярное выражение в шаблон с заданными флагами.
     * */
    private final static Pattern KEYS = Pattern.compile("\\$\\{.+?\\}");

    @Override
    public String generate(String template, Map<String, String> data) {
        Matcher matcher = KEYS.matcher(template);
        while (matcher.find()) {
            String key = matcher.group();
//            if (!data.containsKey(key)) {
//                try {
//                    throw new Exception("Map does't contain that keys");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
            if (!data.get(matcher.group()).equals(key)) {
                    new RuntimeException("invalid parameters");
            }
            template = template.replaceFirst(KEYS.pattern(), data.get(matcher.group()));
        }
        return template;
    }
}
