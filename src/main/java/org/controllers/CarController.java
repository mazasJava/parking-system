package org.controllers;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import javafx.fxml.FXML;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.models.Car;
import org.models.History;

import java.io.IOException;
import java.util.*;

import static com.mongodb.client.model.Projections.fields;


import com.mongodb.client.MongoCollection;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Aggregates.*;

public class CarController {

    /**
     * @throws IOException Creat car object and insert object in data base
     */
    @FXML
    public static Car createCar(ObjectId id, String matricule) throws IOException {

        MongoCollection<Car> carMongoCollection = DbConnection.database.getCollection("cars", Car.class);

        Car newCar = new Car().setId(id).setMatricule(matricule);
        BasicDBObject checkMatriculIfExists = new BasicDBObject("matricule", matricule);

        FindIterable<Car> test = carMongoCollection.find(checkMatriculIfExists);

        if (test.cursor().hasNext()) {
            System.out.println(test.cursor().next().getId());
            HistoryController.setCarHistorique(test.cursor().next().getId());
            System.out.println("exist");
            return null;
        } else {
            try {
                carMongoCollection.insertOne(newCar);
                System.out.println("Successfully inserted Car documents. \n");
                HistoryController.setCarHistorique(newCar.getId());
                return newCar;
            } catch (Exception e) {
                return null;
            }
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
        List<History> cars = historyMongoCollection
                .aggregate(Arrays.asList(Aggregates.lookup("cars", "carId", "_id", "matricule"), project))
                .into(new ArrayList<>());

        System.out.println("==> Car List with history");


//          retrieve selected element
//        for (History car : cars) System.out.println(car.getMatricule());
        return cars;
    }
    /**
     * Find and update car release date with current date
     */
    public static void releaseCarFromDataBase(String mat) {
        Date date = new Date(System.currentTimeMillis());

        MongoCollection<Car> carMongoCollection = DbConnection.database.getCollection("cars", Car.class);
        MongoCollection<History> historyMongoCollection = DbConnection.database.getCollection("historys", History.class);

        Car carMat = carMongoCollection.find(eq("matricule", mat)).first();

        assert carMat != null;
        ObjectId id = new ObjectId(String.valueOf(carMat.getId()));
        historyMongoCollection.updateOne(Filters.eq("carId", id), Updates.set("dateRelease", date));
        System.out.println("Document update successfully...");
    }


    public static void main(String[] args) throws IOException {
        DbConnection.connect();
//        for (History history : getCarsWithHistorique()) history.getMatricule();
//        createCar(new ObjectId(), "1293N");
//        createCar(new ObjectId(), "22KSI");
//        createCar(new ObjectId(), "912WN");
//        createCar(new ObjectId(), "33267");
//        createCar(new ObjectId(), "88995");
//        createCar(new ObjectId(), "135G6");
//        createCar(new ObjectId(), "001F3");

        releaseCarFromDataBase("22KSI");
    }
}
