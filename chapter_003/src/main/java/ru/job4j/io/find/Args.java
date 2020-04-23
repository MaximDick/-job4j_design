package ru.job4j.io.find;

import org.apache.commons.cli.*;

public class Args {

    private String[] argumets;
    private String keyDir = "-d";
    private String keyName = "-n";
    private String keyLog = "-o";
    private String keyMask = "-m";

    public Args(String[] arguments) {
        this.argumets = arguments;
    }

    public String directory() {
        return checkArgs(keyDir);
    }

    public String nameFile() {
        return checkArgs(keyName);
    }

    public boolean mask() {
        for (String str : this.argumets) {
            if (str.equals(keyMask)) {
                return true;
            }
        }
        return false;
    }

    public String exclude() {
        return checkArgs("-e");
    }

    public String output() {
        return checkArgs(keyLog);
    }

    public String checkArgs(String arg) {
        String result = null;
        for (int i = 0; i < this.argumets.length; i++) {
            if (this.argumets[i].equals(arg)) {
                result = this.argumets[i + 1];
                break;
            }
        }
        return result;
    }
}
