package org.clientServer;

import com.mongodb.client.MongoCollection;
import org.controllers.DbConnection;
import org.models.Car;
import org.models.Client;
import org.models.Parking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {

    /**
     * read the message received from NODEjs api
     * @param stream
     * @return
     * @throws IOException
     */
    public static String inputStreamAsString(InputStream stream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        String line = null;
        line = br.readLine();

        br.close();
        return line.toString();
    }

    /**
     * repeat the process and waiting for other clients to send news
     */
    @Override
    public void run() {
        Parking park = new Parking("this is the parking name",100);
        MongoCollection<Car> clientMongoCollection = DbConnection.database.getCollection("cars", Car.class);
        List<Car> cars = clientMongoCollection.find().into(new ArrayList<>());
        try {
            Parking.addAllCar(cars);
        } catch (IOException e) {
            e.printStackTrace();
        }
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