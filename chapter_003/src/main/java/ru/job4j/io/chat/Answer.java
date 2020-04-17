package ru.job4j.io.chat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Answer {
    /**
     * Файл с ответами.
     * */
    private File source;

    /**
     * Контейнер хранилища ответов.
     * */
        List<String>  dictionary;

        /**
         * Конструктор.
         * @param path путь к файлу с ответами.
         * */
        public Answer(String path) {
            this.source = new File(path);
            this.dictionary = answers();
        }

        /**
         *Считывает данные из файла и добавляет в словарь.
         *
         * @return список возможных ответов.
         * */

        private List<String> answers() {
            List<String> list = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(this.source))) {
                 reader.lines().forEach(list::add);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        }

        /**
         * Возвращает случайный ответ из словаря.
         *
         * @return возвращает случайный ответ.
         * */
        public String answer() {
            return dictionary.get(new Random().nextInt(dictionary.size()));
        }

        /**
         * Возвращает список всех ответов.
         * @return список всех ответов.
         * */
        public List<String> answerList() {
            return this.dictionary;
        }

}
