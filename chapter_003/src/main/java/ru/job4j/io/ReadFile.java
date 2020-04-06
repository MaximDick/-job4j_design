package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

/**
 * 0. Read file[#241611]
 * */
public class ReadFile {
    public static void main(String[] args) {
        FileInputStream in = null;
        /**
         * Чтоб каждый раз не обращаться за 1 байтом, а взять кучу и из нее высчитывать из памяти по байтого
         * для этого и использовал
         * @param BufferedInputStream buff*/
        BufferedInputStream buff;
        try {
            in = new FileInputStream("input.txt");
            buff = new BufferedInputStream(in);
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = buff.read()) != -1) {
                text.append((char) read);
            }

            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                int numb = Integer.parseInt(line);
//                if (numb % 2 == 0) {
////                    System.out.println(numb + "  even");
////                } else {
////                    System.out.println(numb + " not even");
////                }
               String rsl =  numb % 2 == 0 ?  "  even" :  " not even";
                System.out.println(numb + rsl);
            }
        } catch (Exception e) {
            System.out.println("Could not read file: " + e.toString());
        } finally {
            /**
             * Закрытие потока.
             * */
            if (in != null) {
                try {
                    in.close();
                } catch (Exception el) {
                    el.printStackTrace();
                }
            }
        }
    }
}
