package org.controllers;

import com.mongodb.client.MongoCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.models.Client;
import org.models.History;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    @FXML
    TableView tableView;
    @FXML
    TableColumn<Client,String> colName,colEmail,colCarPlate;


    private void show(List<Client> list) {
        tableView.setItems(FXCollections.observableArrayList(list));
    }

    public static List<Client> getClientList() {
        MongoCollection<Client> clientMongoCollection = DbConnection.database.getCollection("clients", Client.class);
        List<Client> clients = clientMongoCollection.find().into(new ArrayList<>());
        return clients;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCarPlate.setCellValueFactory(new PropertyValueFactory<Client, String>("carPlate"));
        colName.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
        show(getClientList());
    }
}