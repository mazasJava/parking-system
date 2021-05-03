package org.controllers;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;
import org.models.Client;
import org.models.History;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClientController {

    public static List<Client> getClientList()
    {
        MongoCollection<Client> clientMongoCollection = DbConnection.database.getCollection("clients", Client.class);
        List<Client> clients = clientMongoCollection.find().sort(Sorts.ascending("timeStamp")).into(new ArrayList<>());
//        List<Client> clients = clientMongoCollection.find().into(new ArrayList<>());
        return clients;
    }

    public static void main(String[] args) {
        DbConnection.connect();
        System.out.println(getClientList());
    }

}
