package org.clientServer;

import org.controllers.CarController;
import org.controllers.DashboardController;
import org.controllers.DbConnection;
import org.models.Car;
import org.models.Parking;

import java.net.*;
import java.io.*;

public class Client extends Thread{
    private static Socket socket;
    private static BufferedReader reader;

    public static Socket getSocket() { return socket; }

    public static BufferedReader getReader() {
        return reader;
    }

    public static String readMEssage(BufferedReader reader, int number) throws IOException {
            String matricule = reader.readLine();
        return matricule;
    }

    public static BufferedReader getReader(Socket socket) {
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
        String hostname = "127.0.0.1";
        int port = Integer.parseInt(String.valueOf(5000));
        Client c = new Client(hostname, port);
        Parking park = new Parking("this is the parking name",10);
        DbConnection.connect();
        c.start();
    }

    @Override
    public void run() {
        while (true){
            try {
                Parking.carIn(Client.readMEssage(Client.getReader(Client.getSocket()), 10));
                System.out.println(DashboardController.class.getDeclaredField("pieChart"));
//                DashboardController.refresh();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}