package org.clientServer;

import org.controllers.DbConnection;
import org.models.Car;
import org.models.Parking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    public static void main(String[] args) {
//        DbConnection.connect();

        Test test = new Test();
        test.start();
    }

    public static String inputStreamAsString(InputStream stream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();
        String line = null;

//        while ((line = br.readLine()) != null) {
        line = br.readLine();
//            sb.append(line + "\n");
//        }

        br.close();
        return line.toString();
    }

    @Override
    public void run() {
        Parking park = new Parking("this is the parking name",100);
//        super.run();
        try {
            ServerSocket server;
            server = new ServerSocket(8080);
            int i = 0;
            while (true) {
                System.out.println("CLient n " + i++);
                Socket client;
                InputStream input;
                client = server.accept();
                input = client.getInputStream();
                String inputString = Test.inputStreamAsString(input);
                park.carIn(inputString);
                System.out.println(inputString);
                Parking.carIn(inputString);
                client.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}