package org.controllers;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import javafx.fxml.FXML;
import org.bson.Document;
import org.bson.codecs.Encoder;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.mainapp.App;
import org.models.Car;

import java.io.IOException;
import java.util.*;

import static java.util.List.*;


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
    public void createCar() throws IOException {
        MongoCollection<Document> collection = database.getCollection("cars");

        Car carObject;
        carObject = new Car(id, "test103");
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
        mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://mehdi-java:Password1234@cluster0.dw27l.mongodb.net"));
        database = mongoClient.getDatabase("PARKING_MANAGEMENT_SYSTEM");
        System.out.println("get car list :");
        MongoCollection<Document> collection = database.getCollection("historiques");
//        AggregateIterable<Document> result = collection.aggregate(Collections.singletonList(new Document("$lookup",
//                new Document("from", "cars")
//                        .append("localField", "car_id")
//                        .append("foreignField", "_id")
//                        .append("as", "carhistory"))));

//        AggregateIterable<Document> we = collection.aggregate(
//                Arrays.asList(
//                        Aggregates.match(Filters.eq("categories", "Bakery")),
//                        Aggregates.group("$stars", Accumulators.sum("count", 1))
//                )
//        );
        //                        Projections.include("carhistory.matricule"),
        AggregateIterable<Document> tr = collection.aggregate(List.of(Aggregates.lookup("cars", "car_id", "_id", "carhistory"), Aggregates.project(Projections.fields(
                Projections.excludeId(),
                Projections.include("state"),
                Projections.include("dateEntered"),
                Projections.include("dateRelease"),
//                        Projections.include("carhistory.matricule"),
                Projections.computed(
                        "carhistory",
                        new Document("$arrayElemAt", Arrays.asList("$carhistory.matricule", 0))
                )
        ))));

//        MongoCursor<Document> cursor = collection2.find().iterator();
//        try (MongoCursor<Document> iterator = result.iterator()) {
//            while (iterator.hasNext()) {
//                Document temp_doc = iterator.next();
////                Document temp_address_doc = temp_doc.get("carhistory",Document.class);
////                String address = temp_address_doc.getString("matricule");
//
//
////                Document de = (Document) temp_doc.get("carhistory");
//
////                Document doc = (Document) temp_person_doc.get("carhistory");
////                String houseNo= doc.getString("matricule");
//                System.out.println(temp_doc);
//            }
//        }

        for (Document document : tr) {
            System.out.println(document);
        }
    }

    public static void main(String[] args) {
        getCarsWithHistorique();
    }



}
