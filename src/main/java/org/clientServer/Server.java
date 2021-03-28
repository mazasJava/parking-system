package org.clientServer;

import org.models.Car;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Server {

    public static void main(String[] args) {

        int port = 8081;
        final String CHAR_UPPERCASE = "abcdefghijklmnopqrstuvwxyz".toUpperCase();
        final char separator = '/';
        final int PASSWORD_LENGTH = 10;
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                for (int i = 0; i < 5; i++) {

                    StringBuilder result = new StringBuilder(PASSWORD_LENGTH);
//                    City Identifier
                    for (int j = 0; j < 2; j++) {
                        Random r = new Random();
                        int index = r.nextInt(10);
                        result.append(index);
                    }
                    result.append(separator);
//                    State Identifier
                    Random r = new Random();
                    int index = r.nextInt(10);
                    result.append(CHAR_UPPERCASE.charAt(index));
                    result.append(separator);
//                    Registration number
                    for (int j = 0; j < 5; j++) {
                        Random rand = new Random();
                        int num = rand.nextInt(10);
                        result.append(num);
                    }
//                    ***********************
                    writer.println(result);
//                    writer.println(result + " =>" + idCar);
                    Thread.sleep(4000);
                }
            }

        } catch (IOException | InterruptedException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}