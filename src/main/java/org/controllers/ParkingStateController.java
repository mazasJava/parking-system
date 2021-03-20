package org.controllers;

import javafx.fxml.FXML;
import org.mainapp.App;

import java.io.IOException;

public class ParkingStateController {
    @FXML
    public void switchToParkingCar() throws IOException {
        App.setRoot("car");
    }
    @FXML
    public void switchToParkingState() throws IOException {
        App.setRoot("parkingState");
    }
}
