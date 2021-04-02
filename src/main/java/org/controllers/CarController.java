package org.controllers;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import com.mongodb.client.result.UpdateResult;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import org.bson.Document;
import org.bson.codecs.Encoder;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.mainapp.App;
import org.models.Car;
import org.models.Historique;

import java.io.IOException;
import java.util.*;

import static java.util.List.*;
//import static com.mongodb.client.model.Filters.*;
//import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;


public class CarController {
    static MongoClient mongoClient;
    static MongoDatabase database;
    //    public MongoDatabase database = DbConnection.getDatabase();
    public static final ObjectId id = new ObjectId();

    @FXML
    public void switchToParkingState() throws IOException {
        App.setRoot("parkingState");
    }

    @FXML
    public void switchToParkingCar() throws IOException {
        App.setRoot("car");
    }

    @FXML
    public static void createCar() throws IOException {
        mongoClient = DbConnection.getConnection();
        database = DbConnection.getDatabase();
        MongoCollection<Document> collection = database.getCollection("cars");

        Car carObject = new Car(id, "AKMRBUTTER8");
        Document car = new Document();

        car.append("_id", carObject.getId())
                .append("matricule", carObject.getmatricule());
        try {
            collection.insertOne(car);
            HistoriqueController.setCarHistorique(carObject.getId());
            System.out.println("Successfully inserted documents. \n");
            getCarsWithHistorique();
        } catch (MongoWriteException mwe) {
            if (mwe.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                System.out.println("Document with that id already exists");
            }
        }
    }

    @FXML
    public static void getCarsWithHistorique() {
        mongoClient = DbConnection.getConnection();
        database = DbConnection.getDatabase();
        System.out.println("get car list :");
        MongoCollection<Document> collection = database.getCollection("historiques");
        AggregateIterable<Document> tr = collection.aggregate(List.of(Aggregates.lookup("cars", "car_id", "_id", "carhistory"), Aggregates.project(Projections.fields(
                Projections.excludeId(),
                Projections.include("state"),
                Projections.include("dateEntered"),
                Projections.include("dateRelease"),
                Projections.computed(
                        "carhistory",
                        new Document("$arrayElemAt", Arrays.asList("$carhistory.matricule", 0))
                )
        ))));
        List<Car> carList = new ArrayList<>();
        List<Historique> historiqueList = new ArrayList<>();

        for (Document document : tr) {
            Historique carHistoricList = new Historique(id, document.getString("state"), document.getDate("dateEntered"), document.getDate("dateRelease"));
            Car carList2 = new Car(id, document.getString("carhistory"), carHistoricList);
            historiqueList.add(carHistoricList);
            carList.add(carList2);
        }
        System.out.println(carList.toString());

    }


    public static void main(String[] args) throws IOException {
        DbConnection.connect();
        createCar();
//        getCarsWithHistorique();
    }
}
