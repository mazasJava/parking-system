package org.controllers;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;
import org.models.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientController {


    public static List<Client> getClientsList(int pageSize) {
        MongoCollection<Client> clientMongoCollection = DbConnection.database.getCollection("clients", Client.class);
        List<Client> clients = clientMongoCollection.find()
                .sort(Sorts.ascending("timeStamp"))
                .limit(pageSize).into(new ArrayList<>());
        return clients;
    }

    public static void main(String[] args) {
        DbConnection.connect();
        System.out.println(getClientsList(6));
    }

}
