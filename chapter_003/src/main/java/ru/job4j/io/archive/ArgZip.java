package ru.job4j.io.archive;

import java.util.HashMap;
import java.util.Map;

public class ArgZip {

//    private final String[] arguments;
//    private String keyDir = "-d";
//    private String keyLog = "-o";
//
//    public ArgZip(String[] arguments) {
//        this.arguments = arguments;
//    }
//
//

//
//    /**
//     * -d - directory - которую мы хотим архивировать.
//     */
//    public String directory() {
//        return checkArgs(keyDir);
//    }
//
//    /**
//     * -e - exclude - исключить файлы *.xml.
//     */
//    public String exclude() {
//        return checkArgs("-e");
//    }
//
//    /**
//     * -o - output - во что мы архивируем.
//     */
//    public String output() {
//        return checkArgs(keyLog);
//    }
//
//    public String checkArgs(String arg) {
//        String result = null;
//        for (int i = 0; i < this.arguments.length; i++) {
//            if (arguments[i].equals(arg)) {
//                result = arguments[i + 1];
//                break;
//            }
//        }
//        return result;
//    }
    private String[] args;
    private Map<String, String> mapArgs = new HashMap<>();

    public ArgZip(String[] args) {
        this.args = args;
        this.mapInitial();
    }

    public boolean valid() {
        boolean rsl = true;
        if (this.args.length != 6) {
            rsl = false;
            System.out.println("args doesn't contains enough \\\"Args\\");
        }
        return rsl;
    }

    private void mapInitial() {
        for (int i = 0; i < this.args.length - 1; i += 2) {
            this.mapArgs.put(this.args[i], this.args[i + 1]);
        }
    }

    public String directory() {
        return this.mapArgs.get("-d");
    }

    public String exclude() {
        String s = this.mapArgs.get("-e");
        return s.substring(s.lastIndexOf("."));
    }

    public String output() {
        return this.mapArgs.get("-o");
    }


}


