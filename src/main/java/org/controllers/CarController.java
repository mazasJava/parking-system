package org.controllers;

import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
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
//        colNumber.setCellValueFactory(new PropertyValueFactory<History, String>("counterId"));
        colCarPlate.setCellValueFactory(new PropertyValueFactory<History, String>("matricule"));
        colDateEntree.setCellValueFactory(new PropertyValueFactory<History, String>("dateEntered"));
        colDateSortie.setCellValueFactory(new PropertyValueFactory<History, String>("dateRelease"));
//        data = FXCollections.observableArrayList(attend);
//        tableView.setItems(data);
        show();
    }

    /**
     * @throws IOException Creat car object and insert object in data base
     */
    @FXML
    public static Car createCar(ObjectId id, String matricule) throws IOException {

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
        return results = historyMongoCollection.aggregate(Arrays.asList(Aggregates.lookup("cars", "carId", "_id", "matricule"), project)).into(new ArrayList<>());
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


}
