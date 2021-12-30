package edu.bsuir.jdbcserver.serverthreadsfactory;

import edu.bsuir.jdbcserver.serverthreads.ServerThread;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    public static final int PORT = 8080;


    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(PORT);

        try {
            while (true) {

                System.out.println("Сервер ждет запрос на соединение");

                Socket socket = server.accept();

                System.out.println("Новая нить обработки запроса создана");

                try {

                    new ServerThread(socket);

                } catch (IOException e) {

                    socket.close();

                }
            }
        }
        finally {

            server.close();

            System.out.println("Сервер прекратил работу");

        }
    }
}

