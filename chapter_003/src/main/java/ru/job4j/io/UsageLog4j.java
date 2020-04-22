package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
//        LOG.trace("trace message");
//        LOG.debug("debug message");
//        LOG.info("info message");
//        LOG.warn("warn message");
//        LOG.error("error message");
        String name = "Ivan Petrov";
        int age = 20;
        double height = 6.0;
        boolean isMarried = false;
        char startName = 'I';
        byte optionA = 0b0100;
        short shortValue = -32767;
        long longValue = 100L;
        float floatValue = 8.5F;
        LOG.debug("User info name : {}, age : {}", name, age);
        LOG.debug("optionA : {}", optionA);
        LOG.debug("isMarried : {}", isMarried);
        LOG.debug("User name begins with : {}", startName);
        LOG.debug("Short value is : {}", shortValue);
        LOG.debug("Long value is : {}", longValue);
        LOG.debug("Float value is : {}", floatValue);
    }
}
