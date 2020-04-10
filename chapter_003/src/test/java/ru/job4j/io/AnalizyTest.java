package ru.job4j.io;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;
import static junit.framework.TestCase.assertEquals;


public class AnalizyTest {
    private Analizy analizy;

    @Before
    public void init() {
        analizy = new Analizy();
    }

    @Test
    public void whenServerUnavailable() {
        String source = "./data/server.log";
        String target = "./data/unavailable.csv";
        analizy.unavailable(source, target);
        try (Scanner scanner = new Scanner(new File(target))) {
            while (scanner.hasNext()) {
                assertEquals(scanner.nextLine(), "10:57:01-10:59:01;");
                assertEquals(scanner.nextLine(), "11:01:02-11:02:02;");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



//    @Test
//    public void csvTest() {
//        List<String> times = List.of(
//                "10:58:01; 10:59:01",
//                "11:01:02; 11:02:02"
//        );
//        String source = ".\server.log";
//        String target = ".\\unavailable.csv";
//        Analizy analizy = new Analizy();
//        analizy.unavailable(source, target);
//        String line;
//        int i = 0;
//        try (BufferedReader read = new BufferedReader(new FileReader(target))) {
//            while ((line = read.readLine()) != null) {
//                assertThat(line, is(times.get(i)));
//                i++;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}