package org.controllers;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.fields;

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
        MongoCollection<History> historyMongoCollection = DbConnection.database.getCollection("historys", History.class);
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
        String formattedDate = formatter.format(date);
        MongoCollection<Car> carMongoCollection = DbConnection.database.getCollection("cars", Car.class);
        MongoCollection<History> historyMongoCollection = DbConnection.database.getCollection("historys", History.class);

        Car carMat = carMongoCollection.find(eq("matricule", mat)).first();

        if (carMat == null) throw new AssertionError();
        ObjectId id = new ObjectId(String.valueOf(carMat.getId()));
        historyMongoCollection.updateOne(Filters.eq("carId", id), Updates.set("dateRelease", formattedDate));
        System.out.println("Document update successfully...");
    }


    /**
     * Search in cars table and history table with the given parameter
     *
     * @param query
     * @return
     */
//    public static List<History> search(String query) {
//
//
//        List<History> tr;
//        MongoCollection<History> historyMongoCollection = DbConnection.database.getCollection("historys", History.class);
//        MongoCollection<Car> carMongoCollection = DbConnection.database.getCollection("cars", Car.class);
//
//        historyMongoCollection.createIndex(Indexes.text("dateEntered"));
//        carMongoCollection.createIndex(Indexes.text("matricule"));
//        String result;
//        try {
//            MongoCursor<History> cursorHistory = null;
//            MongoCursor<Car> cursorCar = null;
//            cursorHistory = historyMongoCollection.find(new Document("$text", new Document("$search", query).append("$caseSensitive", false).append("$diacriticSensitive", false))).iterator();
//            cursorCar = carMongoCollection.find(new Document("$text", new Document("$search", query).append("$caseSensitive", false).append("$diacriticSensitive", false))).iterator();
//            if (cursorHistory.hasNext()) while (cursorHistory.hasNext()) {
//                result = (String) cursorHistory.next().getDateEntered();
//                System.out.println(result);
//            }
//            else if (cursorCar.hasNext()) {
//                while (cursorCar.hasNext()) {
//                    Car car = cursorCar.next();
//                    ObjectId carId = car.getId();
//                    String mat = car.getMatricule();
//                    historyMongoCollection.find(eq("carId", carId)).first();
//                    tr = historyMongoCollection.find(eq("carId", carId)).into((new ArrayList<>()));
//
//                    tr.get(i++).setMatricule(mat);
//                    System.out.println(tr);
//
//                    return tr;
//                }
//            } else System.out.println("not found");
//            cursorHistory.close();
//            cursorCar.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    public static List<History> searchCar(String query) {
//        for (History carInfo : results) {
//            if (carInfo.getMatricule().equals(query)) {
////                System.out.println(carInfo);
//                results2.add(carInfo);
//            } else if (carInfo.getDateEntered().equals(query)) {
////                System.out.println(carInfo);
//                results2.add(carInfo);
//            }
//        }
//        return results2;
//    }

    /**
     * Pagination method update result collection with the page passed in the parameter
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public static List<History> pagination(int pageNumber, int pageSize) {
        MongoCollection<History> historyMongoCollection = DbConnection.database.getCollection("historys", History.class);
        try {
            results = historyMongoCollection.aggregate(Arrays.asList(
                    lookup("cars", "carId", "_id", "matricule"),
                    skip(pageSize * (pageNumber - 1)),
                    limit(pageSize),
                    project(fields(Projections.include("carId"), Projections.include("dateEntered"),
                            Projections.include("dateRelease"), Projections.computed("matricule",
                                    new Document("$arrayElemAt", Arrays.asList("$matricule.matricule", 0)))))
            )).into(new ArrayList<>());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }


    public static void main(String[] args) throws IOException, ParseException {
        DbConnection.connect();
//        getCarsWithHistorique();
//        createCar(new ObjectId(), "10/S/123498");
//        System.out.println(search("02/05/2021"));
//        System.out.println(search("10/H/47424"));
//        search("02/05/2021");
//        System.out.println(pagination(3, 5));

//        releaseCarFromDataBase("40/X/179552");

//        createCar(new ObjectId(),"40/X/179552");
//        createCar(new ObjectId(),"90/S/123498");
//        createCar(new ObjectId(),"20/R/474245");


//        System.out.println("-------------------------------------------");
//        System.out.println("-------------------------------------------");
//        System.out.println("-------------------------------------------");
//        System.out.println("-------------------------------------------");
//        search("40/X/179552");


    }

}
