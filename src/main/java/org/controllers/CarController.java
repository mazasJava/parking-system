package org.controllers;

import javafx.fxml.FXML;
import org.mainapp.App;

import java.io.IOException;

public class CarController {
    @FXML
    public void switchToParkingState() throws IOException {
        App.setRoot("parkingState");
    }
    @FXML
    public void switchToParkingCar() throws IOException {
        App.setRoot("car");
    }
}
