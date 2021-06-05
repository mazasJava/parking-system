package org.controllers;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Projections;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.Document;
import org.models.Car;
import org.models.Client;
import org.models.History;

import java.net.URL;
import java.util.*;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Projections.fields;

public class ClientController implements Initializable {
    @FXML
    TableView tableView;
    @FXML
    TableColumn<Client,String> colName,colEmail,colCarPlate;
    @FXML
    private Pagination pagination;

    /**
     * show the list passed in parameter
     * @param list
     */
    private void show(List<Client> list) {
        tableView.refresh();
        tableView.setItems(FXCollections.observableArrayList(list));
    }

    /**
     * get the full list of clients from the  database
     *
     * @return
     */
    public static List<Client> getClientList() {
        MongoCollection<Client> clientMongoCollection = DbConnection.database.getCollection("clients", Client.class);
        List<Client> clients = clientMongoCollection.find().into(new ArrayList<>());
        return clients;
    }

    /**
     * realise the pagination basing on the page size and number
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public  List<Client> pagination(int pageNumber, int pageSize) {
        MongoCollection<Client> clientMongoCollection = DbConnection.database.getCollection("clients", Client.class);
        List<Client> clients = clientMongoCollection.find().skip(pageSize * (pageNumber - 1)).limit(pageSize).into(new ArrayList<>());
        return clients;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCarPlate.setCellValueFactory(new PropertyValueFactory<Client, String>("carPlate"));
        colName.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
        show((pagination(1, 2)));
        pagination.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                show((pagination((int) newValue+1, 2)));
            }
        });
    }
}