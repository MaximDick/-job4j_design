package ru.job4j.io.archive;

import ru.job4j.io.search.Search;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private final ArgZip argZip;

    public Zip(ArgZip argZip) {
        this.argZip = argZip;
    }

    public void packFile(List<File> source, File target) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
                        try {
                            for (File file : source) {
                            zos.putNextEntry(new ZipEntry(file.getAbsolutePath().substring(this.argZip.directory().length() + 1)));
                            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
                                zos.write(bis.readAllBytes());
                            }
                        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }




    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getAbsolutePath().substring(argZip.directory().length() + 1)));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<File> seekBy(String parent, String exts) {
        List<File> rsl = new ArrayList<>();
        File file = new File(parent);
        Queue<File> data = new LinkedList();
        data.offer(file);
        while (!data.isEmpty()) {
            File el = data.poll();
            if (!el.isDirectory()) {
                String name = el.getName();
                if (name.contains(".")) {
                    if (!exts.contains(name.substring(name.indexOf(".")))) {
                        rsl.add(el);
                    }
                }
            } else {
                for (File child : el.listFiles()) {
                    data.offer(child);
                }
            }
        }
        return rsl;
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip(new ArgZip(args));

        List<File> resources = zip.seekBy(zip.argZip.directory(), zip.argZip.exclude());

        zip.packFile(resources, new File(zip.argZip.output()));

        new Zip(new ArgZip(args)).packSingleFile(
                new File("c:\\projects\\job4j_design\\chapter_003\\pom.xml"),
                new File(".\\chapter_003\\pom.zip")
        );


    }

}
