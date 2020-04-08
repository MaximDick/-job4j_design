package ru.job4j.test;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class AnagramTest {

    @Test
    public void isAnagramTest() {
        Anagram anagram = new Anagram();
        assertThat(anagram.checkAnargam("dog", "god"), is(true));
        assertThat(anagram.checkAnargam("амам", "мама"), is(true));
        assertThat(anagram.checkAnargam(null, null), is(true));
        assertThat(anagram.checkAnargam("", ""), is(true));
    }

}