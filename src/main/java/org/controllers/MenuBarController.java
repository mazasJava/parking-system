package org.controllers;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import org.bootstrap.App;

import java.io.IOException;

public class MenuBarController {

    @FXML
    private Button btnswitchToParkingDashboard;

    @FXML
    private Button btnswitchToParkingCar;

    @FXML
    private Button btnswitchToParkingState;

    @FXML
    private Button btnswitchToParkingStatistics;

    @FXML
    private Button btnswitchToClients;

    @FXML
    private Button btnswitchToSettings;

    @FXML
    private Button btnClose;

    public void handleClick(MouseEvent event) throws IOException {
        if(event.getSource() == btnswitchToParkingDashboard){
            App.setRoot("dashboard");
//            btnswitchToParkingDashboard.set
        }
        else if(event.getSource() == btnswitchToParkingCar){
            App.setRoot("car");
        }
        else if(event.getSource() == btnswitchToParkingState){
            App.setRoot("parkingState");
        }
        else if(event.getSource() == btnswitchToParkingStatistics){
            App.setRoot("statistics");
        }
        else if(event.getSource() == btnswitchToClients){
            App.setRoot("client");
        }
    }

    public void clickItemSettings(ActionEvent actionEvent) throws IOException {
        App.setRoot("settings");
    }

    public void clickItemClose(ActionEvent actionEvent) throws IOException {
        App.setRoot("login");
    }
}