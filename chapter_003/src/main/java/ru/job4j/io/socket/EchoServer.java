package ru.job4j.io.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.io.UsageLog4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Доработайте класса ru.job4j.io.EchoServer.
 * Если клиент отправлять запрос http://localhost:9000/?msg=Bye нужно завершить работу сервера.*/
public class EchoServer {


    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    /** @noinspection checkstyle:InnerAssignment*/
    public static void main(String[] args)  {


        try (ServerSocket server = new ServerSocket(9000)) {
            boolean flag = false;
            while (!flag) {
                System.out.println("Server started");
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    if (!str.isEmpty()) {
                        String[] line = str.split(" "); // разбиваем строку на массив строк
                        int index = line[1].lastIndexOf('='); // позицию  элемента '='
                        String argument = line[1].substring(index + 1); //извлекаем продстроку из line[1].
                        String answer;
                        if ("Hello".equals(argument)) {
                            answer = "Hello, dear friend.";

                        } else if ("Exit".equals(argument)) {
                            answer = "Exit. Server shut down.";

                            flag = true;
                        } else {
                            answer = argument;

                        }
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        out.write((answer + "\r\n\r\n").getBytes());
                        System.out.println(str);
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("Error IOException ", e);
        }
    }
}

