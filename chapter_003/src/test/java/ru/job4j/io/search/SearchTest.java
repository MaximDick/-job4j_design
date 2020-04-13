package ru.job4j.io.search;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SearchTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private File docFile, anotherTxtFile, javaFile;

    @Before
    public void setUp() throws Exception {

        this.docFile = folder.newFile("one.doc");
        this.anotherTxtFile = folder.newFile("two.doc");
        this.javaFile = folder.newFile("three.java");

    }

    @Test
    public void textTxtFiles() throws IOException {
        assertThat(Search.search(Paths.get(folder.getRoot().getAbsolutePath()), ".doc"),
                is(List.of(docFile.getAbsolutePath(), anotherTxtFile.getAbsolutePath())));
    }

    @Test
    public void testJavaFiles() throws IOException {
        assertThat(Search.search(Paths.get(folder.getRoot().getAbsolutePath()), ".java"),
                is(List.of(javaFile.getAbsolutePath())));
    }

    @Test
    public void testNotFoundFiles() throws IOException {
        assertThat(Search.search(Paths.get(folder.getRoot().getAbsolutePath()), ".js"),
                is(List.of()));
    }
}