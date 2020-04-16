package ru.job4j.io.search;

import com.sun.jdi.PathSearchingVirtualMachine;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * task 3. Сканирование файловой системы.[#241605]
 * */
public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        Files.walkFileTree(start, new PrintFiles());
    }

    public static List<String> search(Path root, String ext) throws IOException {
        List<String> result = new ArrayList<>();
        Files.walkFileTree(root, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                String fileName = file.toString();
                if (fileName.endsWith(ext)) {
                    result.add(fileName);
                }
                return CONTINUE;
            }
        });
        return result;
    }

}
