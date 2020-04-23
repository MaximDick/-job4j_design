package ru.job4j.io.archive;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class ArgZip {

    private final String[] arguments;
    private String keyDir = "-d";
    private String keyLog = "-o";

    public ArgZip(String[] arguments) {
        this.arguments = arguments;
    }


    public boolean valid() {
        boolean rsl = true;
        if (this.arguments.length != 6) {
            rsl = false;
            System.out.println("args doesn't contains enough \\\"Args\\");
        }
        return rsl;
    }

    /**
     * -d - directory - которую мы хотим архивировать.
     */
    public String directory() {
        return checkArgs(keyDir);
    }

    /**
     * -e - exclude - исключить файлы *.xml.
     */
    public String exclude() {
        return checkArgs("-e");
    }

    /**
     * -o - output - во что мы архивируем.
     */
    public String output() {
        return checkArgs(keyLog);
    }

    public String checkArgs(String arg) {
        String result = null;
        for (int i = 0; i < this.arguments.length; i++) {
            if (arguments[i].equals(arg)) {
                result = arguments[i + 1];
                break;
            }
        }
        return result;
    }
}
//        String result = "";
//        if (!Arrays.stream(arguments)
//                .anyMatch(keys -> keys.equals(key))) {
//            throw new NoSuchElementException("Doesn't have contain element");
//        }
//        for (int i = 0; i < arguments.length; i++) {
//            if (arguments[i].equals(key)) {
//                result = arguments[i + 1];
//            }
//        }
//        return result;
//    }
//}
