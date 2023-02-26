package com.github.mahendranv;

public class TestResources {

    private static String asUrl(String relativePath) {
        return TestResources.class.getClassLoader().getResource(relativePath).toString();
    }

    static String RSS_SVK = asUrl("feeds/svk_20230220.xml");
    static String RSS_FRAGMENTED = asUrl("feeds/fragmented.xml");
}
