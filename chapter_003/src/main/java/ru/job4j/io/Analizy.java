package ru.job4j.io;

import java.io.*;

/**
 * 1. Реализуйте метод unavailable.
 *
 * source - имя файла лога
 *
 * target - имя файла после анализа.
 *
 * 2. Метод anavailable должен находить диапазоны, когда сервер не работал.
 *
 * Сервер не работал. если status = 400 или 500.
 *
 * Диапазон считается последовательность статусов 400 и 500
 *
 * Например:
 *
 * 200 10:56:01
 *
 * 500 10:57:01
 *
 * 400 10:58:01
 *
 * 200 10:59:01
 *
 * 500 11:01:02
 *
 * 200 11:02:02
 *
 * тут два периода - 10:57:01 до 10:59:01 и 11:01:02 до 11:02:02
 *
 * Начальное время - это время когда статус 400 или 500. конечное время это когда статус меняется с 400 или 500 на 200 300.
 *
 * 3. Результат анализа нужно записать в файл target.
 *
 * Формат файла
 *
 * начала периода;конец периода;
 *
 * 4. Записать тесты.*/
public class Analizy {

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
            BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            String line;
            boolean marker = false;
            while ((line = read.readLine()) != null) {
                if (line.startsWith("400") || line.startsWith("500")) {
                    if (!marker) {
                        writer.write(line, 4, line.length() - 4);
                        writer.write("-");
                        marker = true;
                    }
                } else {
                        if (marker) {
                        writer.write(line, 4, line.length() - 4);
                        writer.write(";");
                        writer.write(System.lineSeparator());
                        marker = false;
                    }
            }
        }
    } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
