package ru.job4j.io.archive;

import ru.job4j.io.search.Search;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
                            zos.putNextEntry(new ZipEntry(file.getPath()));
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
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Исключение файлов с заданным расширением
     * */
    public List<File> excludesList(Path path, String exclude) throws IOException {
        List<String> result = Search.search(path, "");
        return result.stream()
                .filter(paths -> !paths.endsWith(exclude))
                .map(file -> Paths.get(file).toFile())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip(new ArgZip(args));
        List<File> resources = zip.excludesList(Paths.get(zip.argZip.directory()), zip.argZip.exclude());
        zip.packFile(resources, new File(zip.argZip.output()));


        new Zip(new ArgZip(args)).packSingleFile(
                new File("./chapter_003/pom.xml"),
                new File("./chapter_003/pom.zip")
        );
    }

}
