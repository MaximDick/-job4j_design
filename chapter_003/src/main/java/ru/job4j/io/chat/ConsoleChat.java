package ru.job4j.io.chat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class ConsoleChat {

    /**
     * Слова команды для взаимодействия.
     * */
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private static final String EXIT = "закончить";

    /**
     * Поле для хранения ответов.
     * */
    private Answer answers;

    /**
     * Поток ввода данных.
     * */
   private InputStream in;


   /**
    * Конструктор.
    *
    * @param path путь к файлу.
    * @param input поток ввода данных.
    * */
   ConsoleChat(String path, InputStream input) {
       this.answers = new Answer(path);
       this.in = input;
   }

    /**
     * Осуществляет взаимодействие пользователя с чат-ботом. Слова-команды стоп/продолжить/закончить.
     * Пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла.
     * Бот останавливается, если пользователь вводит слово «стоп», при этом он может продолжать отправлять сообщения в чат.
     * Если пользователь вводит слово «продолжить», программа снова начинает отвечать.
     * При вводе слова «закончить» программа прекращает работу.
     * Запись диалога, включая слова-команды, записывается в текстовый лог.
     *
     * @param target имя лога.
     */
   public void launch(String target) {
       List<String> log = new LinkedList<>();
       try (BufferedReader reader = new BufferedReader(new InputStreamReader((this.in)));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target)))) {
                writer.write(new Date().toString());
                writer.newLine();
                writer.flush();
                String userMsg;
                String botMsg;
                boolean stop = false;
                do {
                    userMsg = reader.readLine();
                    log.add("user: " + userMsg);
                    if (STOP.equals(userMsg)) {
                        stop = true;
                    } else if (CONTINUE.equals(userMsg)) {
                        stop = false;
                    }
                    if (!stop && !EXIT.equals(userMsg)) {
                        botMsg = this.answers.answer();
                        log.add("bot: "  + botMsg);
                        System.out.println("bot: " + botMsg);
                    }
                } while (!EXIT.equals(userMsg));
                    for (String line : log) {
                        writer.write(line + System.lineSeparator());
                    }
                } catch (IOException e) {
           e.printStackTrace();
       }
   }

    public static void main(String[] args) {
        String path = "C:\\projects\\job4j_design\\dictionary.txt";
       // System.out.println(myFile.getCanonicalPath());
        System.out.println(path);
        String target =  System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "chat.log";
        ConsoleChat chat = new ConsoleChat(path, System.in);
        chat.launch(target);


    }
}
