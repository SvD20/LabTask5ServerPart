package edu.bsuir.jdbcserver.serverthreads;

import edu.bsuir.jdbcserver.database.ConnectorDB;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;


public class ServerThread extends Thread {

    private Socket socket = null;
    private BufferedReader in = null;
    private BufferedWriter out = null;
    private ConnectorDB connectorDB = null;

    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        connectorDB = new ConnectorDB();
        start();
    }

    @Override
    public void run() {

        try {
            connectorDB.create_connection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            send(connectorDB.select_from_table(in.readLine()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;

        try {
            connectorDB.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Данные отправлены");

    }


    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {}
    }


}


