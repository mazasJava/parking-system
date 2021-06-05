package org.clientServer;

import org.models.Parking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {


    public static String inputStreamAsString(InputStream stream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        String line = null;
        line = br.readLine();

        br.close();
        return line.toString();
    }

    @Override
    public void run() {
        Parking park = new Parking("this is the parking name",100);
        try {
            ServerSocket server;
            server = new ServerSocket(8080);
            int i = 0;
            while (true) {
                Socket client;
                InputStream input;
                client = server.accept();
                input = client.getInputStream();
                String inputString = Server.inputStreamAsString(input);
                if (Parking.search(inputString) == null) {
                    park.carIn(inputString);
                }else{
                    park.carOut(inputString);
                }
                client.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}