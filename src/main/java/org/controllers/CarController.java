package org.controllers;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.models.Car;
import org.models.History;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.mongodb.client.model.Projections.fields;

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

public class CarController implements Initializable {
    public static List<History> results;
    public static List<History> results2;
    public static final ObjectId id = new ObjectId();
    String Number, CarPlate, DateEntree, DateSortie;
    @FXML
    TableView<History> tableView;
    @FXML
    TableColumn<History, String> colNumber, colCarPlate, colDateEntree, colDateSortie;
    ObservableList<History> data;
    public List attend = new ArrayList();

    public void show() {
        attend.clear();
        data = FXCollections.observableArrayList(getCarsWithHistorique());
        tableView.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // colNumber.setCellValueFactory(new PropertyValueFactory<History,
        // String>("counterId"));
        colCarPlate.setCellValueFactory(new PropertyValueFactory<History, String>("matricule"));
        colDateEntree.setCellValueFactory(new PropertyValueFactory<History, String>("dateEntered"));
        colDateSortie.setCellValueFactory(new PropertyValueFactory<History, String>("dateRelease"));
        // data = FXCollections.observableArrayList(attend);
        // tableView.setItems(data);
        show();
    }

    /**
     * @throws IOException Creat car object and insert object in data base
     */
    @FXML
    public static Car createCar(ObjectId id, String matricule) throws IOException, ParseException {

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
        MongoCollection<History> historyMongoCollection = DbConnection.database.getCollection("historys",
                History.class);
        Bson project = project(fields(Projections.include("carId"), Projections.include("dateEntered"),
                Projections.include("dateRelease"), Projections.computed("matricule",
                        new Document("$arrayElemAt", Arrays.asList("$matricule.matricule", 0)))));
        return results = historyMongoCollection
                .aggregate(Arrays.asList(Aggregates.lookup("cars", "carId", "_id", "matricule"), project))
                .into(new ArrayList<>());
    }

    /**
     * Find and update car release date with current date
     */
    public static void releaseCarFromDataBase(String mat) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String formattedDate= formatter.format(date);
        MongoCollection<Car> carMongoCollection = DbConnection.database.getCollection("cars", Car.class);
        MongoCollection<History> historyMongoCollection = DbConnection.database.getCollection("historys", History.class);

        Car carMat = carMongoCollection.find(eq("matricule", mat)).first();

        if (carMat == null) throw new AssertionError();
        ObjectId id = new ObjectId(String.valueOf(carMat.getId()));
        historyMongoCollection.updateOne(Filters.eq("carId", id), Updates.set("dateRelease", formattedDate));
        System.out.println("Document update successfully...");
    }



    public static List<History> search(String query) {

        MongoCollection<Document> historyMongoCollection = DbConnection.database.getCollection("historys");
        MongoCollection<Document> carMongoCollection = DbConnection.database.getCollection("cars");

        historyMongoCollection.createIndex(Indexes.text("dateEntered"));
        carMongoCollection.createIndex(Indexes.text("matricule"));
        String result;
        try {
            MongoCursor<Document> cursorHistory = null;
            MongoCursor<Document> cursorCar = null;
            cursorHistory = historyMongoCollection.find(new Document("$text", new Document("$search", query).append("$caseSensitive", false).append("$diacriticSensitive", false))).iterator();
            cursorCar = carMongoCollection.find(new Document("$text", new Document("$search", query).append("$caseSensitive", false).append("$diacriticSensitive", false))).iterator();
            if (cursorHistory.hasNext()) while (cursorHistory.hasNext()) {
                result = (String) cursorHistory.next().get("dateEntered");
                return searchCar(result);
            }
            else if (cursorCar.hasNext()) {
                while (cursorCar.hasNext()) {
                    result = (String) cursorCar.next().get("matricule");
                    return searchCar(result);
                }
            } else System.out.println("not found");
            cursorHistory.close();
            cursorCar.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<History> searchCar(String query) {
        for (History carInfo : results) {
            if (carInfo.getMatricule().equals(query)) {
//                System.out.println(carInfo);
                results2.add(carInfo);
            }
            else if(carInfo.getDateEntered().equals(query))
            {
//                System.out.println(carInfo);
                results2.add(carInfo);
            }
        }
        return results2;
    }

    public static void pagination(MongoCollection<Document> cars, int pageNumber, int pageSize) {

        try {

            MongoCursor <Document> cursor = cars.find().skip(pageSize * (pageNumber - 1)).limit(pageSize).iterator();

            while (cursor.hasNext()) {
                Document person = cursor.next();
                System.out.println(person);
            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException, ParseException {
        DbConnection.connect();
//        getCarsWithHistorique();
//        createCar(new ObjectId(), "10/S/123498");
//        System.out.println(search("02/05/2021"));
//        System.out.println(searchCar("02/05/2021"));
//        search("02/05/2021");
        MongoCollection <Document> cars = DbConnection.database.getCollection("cars");
        pagination(cars, 2, 3);

//        releaseCarFromDataBase("40/X/179552");

    }

}
