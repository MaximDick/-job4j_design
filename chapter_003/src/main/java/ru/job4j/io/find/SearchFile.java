package ru.job4j.io.find;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SearchFile {
    private static List<File> result = new ArrayList<>();
    private static final String TF = System.getProperty("java.io.tmpdir");
    private static final String LN = System.getProperty("line.separator");
    private static String path = "";
    private static String nameFile = "";
    private static String log = "";
    private static boolean isExtension = false;

    public static void main(String[] args) {
        run(args);
    }

    private static void run(String[] args) {
        if (isValid(args)) {
            readArgs(args);
            search(path, nameFile, isExtension);
            writeLog(log);
        } else {
            printHelp();
        }
    }

    /**
     * Метод считывания параметров.
     * @param args Входные параметры.
     * */
    private static void readArgs(String[] args) {
        Args arguments = new Args(args);
        path = arguments.directory();
        nameFile = arguments.nameFile();
        log = arguments.output();
        isExtension = arguments.mask();
    }

    /**
     * Метод валидации ключей
     * @param args Входящие параметры.
     * @return Результат валидации.
     * */
    private static boolean isValid(String[] args) {
        return args[0].equals("-d") && args[2].equals("-n")
                && (args[4].equals("-m") || args[4].equals("-f"))
                && args[5].equals("-o");
    }

    /**
     * Метод печати помощи.
     * */
    private static void printHelp() {
        System.out.println("Подсказка: "
                            + LN + "-d - обязательный параметр. Каталог в котором искать."
                            + LN + "-n - обязательный параметр. Маска файла или имя файла."
                            + LN + "-m - искать по маске. либо -f - полное совпадение имени. (Обязательный параметр)"
                            + LN + "-o - обязательный параметр. Название лога. куда будут записаны результаты. Лог-файл появится в temp- папке");
    }

    /**
     * Метод записи результатов в файл.
     * @param outputFile название файла.
     * */
    private static void writeLog(String outputFile) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(TF + File.separator + outputFile))) {
            for (File file : result) {
                out.println(file.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param parent каталог поиска
     * @param pattern полное имя или маска
     * @param isExt true если поиск по маске, false если по полному совпадению.
     * */
    private static void search(String parent, String pattern, Boolean isExt) {
        Search newSearch = new Search();
        result = newSearch.getByPredicate(parent, s1 -> (accept(s1, pattern, isExt)));

        System.out.println("Файлов найдено: " + result.size()
                        + LN + " Результат записан в файл: " + TF + "/" + log);
        }


        private static boolean accept(File name, String nameFile, Boolean isExt) {
            boolean rsl = false;
            if (nameFile.contains("*.")) {
                if (name.getName().endsWith(nameFile.substring(nameFile.lastIndexOf('.')))) {
                    rsl = true;
                }
            }
            return (rsl && isExt) || (name.getName().equals(nameFile) && !isExt);
        }
}
