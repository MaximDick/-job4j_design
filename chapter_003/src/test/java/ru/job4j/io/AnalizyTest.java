package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenServerUnavailable()  throws IOException {
        Analizy analizy = new Analizy();
        String source = "./data/server.log";
        String target = "./data/unavailable.csv";
        analizy.unavailable(source, target);
        List<String> actual = Files.readAllLines(Paths.get(target));
        List<String> expected = List.of("10:57:01-10:59:01;", "11:01:02-11:02:02;");
         assertThat(actual, is(expected));
    }

    @Test
    public void whenServerUnavailableWithTemporaryFolder() throws IOException {
       File source = folder.newFile("source.txt");
       File target = folder.newFile("target.txt");
       try (PrintWriter out = new PrintWriter(source)) {
           out.println("200 10:56:01");
           out.println("200 10:57:01");
           out.println("400 10:58:01");
           out.println("200 10:59:01");
           out.println("500 11:01:02");
           out.println("200 11:02:02");
       }
       String sourcePath = source.getAbsolutePath();
       String targetPath = target.getAbsolutePath();
       new Analizy().unavailable(sourcePath, targetPath);
       List<String> actual = Files.readAllLines(Paths.get(targetPath));
       List<String> expected = List.of("10:58:01-10:59:01;", "11:01:02-11:02:02;");
       assertThat(actual, is(expected));
    }
}