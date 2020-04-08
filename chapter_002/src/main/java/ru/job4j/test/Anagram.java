package ru.job4j.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Anagram {
    /**
     * Проверяет являются ли два слова анаграммой.
     * Анаграмма - это механизм получения нового слово путем перестановки букв.
     *
     * @param str1 второе слово.
     * @param str2 второе слово.
     * @return true если являются анаграммой.
     */
    public boolean checkAnargam(String str1, String str2) {
        boolean rsl = false;
        if (str1 == null) {
            str1 = "";
        }

        if (str2 == null) {
            str2 = "";
        }

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        /** разбивает строку на массив символов.
         * */
        char[] one = str1.toCharArray();
        char[] two = str1.toCharArray();
        if (str1.length() == str2.length()) {
            for (int index = 0; index < str1.length(); index++) {
                map1.putIfAbsent(one[index], 1); //если такой пары нет, то добавляет
                map1.computeIfPresent(one[index], (key, value) -> value + 1);
                map2.putIfAbsent(two[index], 1);
                map2.computeIfPresent(two[index], (key, value) -> value + 1);
            }
            rsl = Objects.equals(map1, map2);
        }
        return rsl;
    }
}
