package org.clientServer;

import org.controllers.DbConnection;
import org.models.Parking;

import java.net.*;
import java.io.*;

public class Client extends Thread {
    private static Socket socket;
    private static BufferedReader reader;

    public static Socket getSocket() {
        return socket;
    }

    public boolean getSocketBoolean() {
        if (getSocket() == null) {
            return false;
        }
        return true;
    }

    public static BufferedReader getReader() {
        return reader;
    }

    public static boolean getReaderBool() {
        if (getReader() == null) {
            return false;
        }
        return true;
    }

    public static String readMEssage(BufferedReader reader) throws IOException {
//        String matricule = reader.readLine();
        return reader.readLine();
    }

    public static BufferedReader getReader(Socket socket) throws IOException {
        InputStream input = null;
        try{
            input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
            return reader;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static BufferedReader initBuffer() throws IOException {
//        try (InputStream input = socket.getInputStream()) {
//            reader = new BufferedReader(new InputStreamReader(input));
//            return reader;
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//        return null;
//    }

    public Client(String hostname, int port) {
        try {
            Client.socket = new Socket(hostname, port);
        } catch (IOException ex) {
            System.exit(0);
        }
    }

    public static void main(String[] args) throws IOException {
        DbConnection.connect();
        String hostname = "127.0.0.1";
        int port = 8081;
        Client c = new Client(hostname, port);
        Parking park = new Parking("this is the parking name", 10);
        c.start();
    }
    @Override
    public void run() {
        while (true) {
            try {
                Parking.carIn((Client.readMEssage(Client.getReader(Client.getSocket()))));
//                System.out.println((Client.readMEssage(Client.getReader(Client.getSocket()))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            }
//        }

    }

}