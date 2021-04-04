package org.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.mainapp.App;

import java.io.IOException;

public class MenuBarController {




    @FXML
    public void switchToParkingDashboard() throws IOException {
        App.setRoot("dashboard");
    }

    @FXML
    public void switchToParkingCar() throws IOException {
        App.setRoot("car");
    }

    @FXML
    public void switchToParkingState() throws IOException {
        App.setRoot("parkingState");
    }

    @FXML
    public void switchToParkingStatistics() throws IOException {
        //App.setRoot("");
    }

    @FXML
    public void switchToErrLog() throws IOException {
        //App.setRoot("");
    }

    @FXML
    public void switchToSettings(ActionEvent actionEvent) {
        //App.setRoot("");
    }


}
