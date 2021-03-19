package org.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.mainapp.App;

public class PrimaryController {


    @FXML
    public void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    @FXML
    public void testi(ActionEvent actionEvent) {
        System.out.println("tets");
    }
}
