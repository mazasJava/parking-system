package org.clientServer;

import org.models.Car;

import java.net.*;
import java.io.*;

public class Client {

    public static void main(String[] args) {

        String hostname = "localhost";
        int port = Integer.parseInt(String.valueOf(8081));

        try (Socket socket = new Socket(hostname, port)) {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            for (int i=0;i<5;i++) {
//                receive the matricule
                String matricule = reader.readLine();
//                create a new car instance
                Car c;
                c = new Car(matricule);
                System.out.println(c);
            }
        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}