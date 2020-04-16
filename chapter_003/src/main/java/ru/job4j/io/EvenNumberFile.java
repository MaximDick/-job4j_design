package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

/**
 * 0. Read file[#241611]
 * */
public class EvenNumberFile {
    public static void main(String[] args) {

        /**
         * Чтоб каждый раз не обращаться за 1 байтом, а взять кучу и из нее высчитывать из памяти по байтого
         * для этого и использовал
         * @param BufferedInputStream buff
         * */

        try (BufferedInputStream buff = new BufferedInputStream(new FileInputStream("even.txt"))) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = buff.read()) != -1) {
                text.append((char) read);
            }

            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                int numb = Integer.parseInt(line);
               String rsl =  numb % 2 == 0 ?  "  even" :  " not even";
                System.out.println(numb + rsl);
            }
        } catch (Exception e) {
            System.out.println("Could not read file: " + e.toString());
        }
    }
}
