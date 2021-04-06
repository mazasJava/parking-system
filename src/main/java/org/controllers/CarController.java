package org.controllers;

import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import javafx.fxml.FXML;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.mainapp.App;
import org.models.Car;
import org.models.History;

import java.io.IOException;
import java.util.*;

import static com.mongodb.client.model.Projections.fields;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static com.mongodb.client.model.Accumulators.push;
import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.descending;

public class CarController {
    public static final ObjectId id = new ObjectId();

    /**
     * @throws IOException Creat car object and insert object in data base
     */
    @FXML
    public static Car createCar(ObjectId id,String matricule) throws IOException {

        MongoCollection<Car> carMongoCollection = DbConnection.database.getCollection("cars", Car.class);

        Car newCar = new Car().setId(id).setMatricule(matricule);
        try {
            carMongoCollection.insertOne(newCar);
            System.out.println("Successfully inserted Car documents. \n");
            HistoryController.setCarHistorique(newCar.getId());
            return newCar;
        } catch (Exception e) {
            return  null;
        }
    }

    /**
     * @return Return car list with information about the cars
     */
    private static List<History> getCarsWithHistorique() {
        MongoCollection<History> historyMongoCollection = DbConnection.database.getCollection("historys", History.class);
        Bson project = project(fields(
                Projections.include("carId"),
                Projections.include("dateEntered"),
                Projections.include("dateRelease"),
                Projections.computed(
                        "matricule",
                        new Document("$arrayElemAt", Arrays.asList("$matricule.matricule", 0))
                )
        ));
        List<History> results = historyMongoCollection.aggregate(Arrays.asList(Aggregates.lookup("cars", "carId", "_id", "matricule"), project)).into(new ArrayList<>());
        System.out.println("==> 3 most densely populated cities in Texas");
        return results;
    }

    /**
     * Find and update car release date with current date
     */
    public static void releaseCarFromDataBase(ObjectId id) {
        Date date = new Date(System.currentTimeMillis());

        MongoCollection<History> collection = DbConnection.database.getCollection("historys", History.class);
        collection.updateOne(Filters.eq("_id", id), Updates.set("dateRelease", date));
        System.out.println("Document update successfully...");
    }

    public static void main(String[] args) throws IOException {
        DbConnection.connect();


        System.out.println(threeMostPopulatedCitiesInTexas().toString());
//        releaseCarFromDataBase();
//        createCar();
//        System.out.println(getCarsWithHistorique().toString());
//        threeMostPopulatedCitiesInTexas(historyMongoCollection);
    }
}
