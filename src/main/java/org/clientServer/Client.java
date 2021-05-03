package org.clientServer;

import org.models.Car;

import java.net.*;
import java.io.*;

public class Client {
    private Socket socket;
    private BufferedReader reader;

    public Socket getSocket() {
        return socket;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void readMEssage(BufferedReader reader,int number) throws IOException {
        for (int i = 0; i < number; i++) {
//                receive the matricule
            String matricule = reader.readLine();
            System.out.println(matricule);
        }
    }

    public BufferedReader getReader(Socket socket) {
        System.out.println("get reader");
        InputStream input = null;
        try {
            input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
            return reader;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Client(String hostname, int port) {
        try {
            this.socket = new Socket(hostname, port);
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {

        String hostname = "localhost";
        int port = Integer.parseInt(String.valueOf(5000));
        Client c = new Client(hostname, port);
        c.readMEssage(c.getReader(c.getSocket()),2);

    }
}