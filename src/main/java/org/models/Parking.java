package org.models;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.controllers.DbConnection;
import org.controllers.HistoryController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Projections.fields;

public class Parking {

    private String adresse;
    private int numberVec;
    public static List<History> results;

    public Parking() {
    }

    public static Car createCar(ObjectId id, String matricule) throws IOException {
        id = new ObjectId("ds");
        MongoCollection<Car> carMongoCollection = DbConnection.database.getCollection("cars", Car.class);
        Car newCar = new Car().setId(id).setMatricule(matricule);
        try {
            carMongoCollection.insertOne(newCar);
            System.out.println("Successfully inserted Car documents. \n");
            HistoryController.setCarHistorique(newCar.getId());
            return newCar;
        } catch (Exception e) {
            return null;
        }
    }

    public Parking(String adresse, int numberVec) {
        this.adresse = adresse;
        this.numberVec = numberVec;
    }

    @Override
    public String toString() {
        return "Parking{" +
                ", adresse='" + adresse + '\'' +
                ", numberVec=" + numberVec +
                '}';
    }

    public static void main(String[] args) {

    }
}
