package ru.job4j.io.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Shell {

    Path currentPath = Paths.get("/");

    Shell cd(final String path) {
        String[] listPaths = path.split(File.separator);
        for (String item : listPaths) {
            if (item.equals("..")) {
                currentPath = currentPath.getParent();
                continue;
            }
            if (item.equals(".")) {
                continue;
            }
            currentPath = Paths.get(currentPath.toString() + File.separator + item);
        }
        path();
        return this;
    }

    public String path() {
        System.out.println(currentPath);
        return this.currentPath.toString();
    }
}
