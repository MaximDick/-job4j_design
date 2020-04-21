package ru.job4j.io.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Доработайте класса ru.job4j.io.EchoServer.
 * Если клиент отправлять запрос http://localhost:9000/?msg=Bye нужно завершить работу сервера.*/
public class EchoServer {
    /** @noinspection checkstyle:InnerAssignment*/
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean flag = false;
            while (!flag) {
                System.out.println("Server started");
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        if (!str.equals("GET /?msg=Bye HTTP/1.1")) {
                            System.out.println(str);
                        } else {
                            System.out.println(str);
                            System.out.println("Server stop");
                            flag = true;
                            break;

                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());

                }
            }
        }
    }
}

