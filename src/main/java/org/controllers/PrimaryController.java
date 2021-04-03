package org.controllers;

import java.io.IOException;
import java.net.URL;
import java.nio.channels.IllegalBlockingModeException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.mainapp.App;

public class PrimaryController {

    @FXML
    Button primaryButton;

    @FXML
    ProgressIndicator progId;

    @FXML
    public void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    ImageView imgLoginProg;
    @FXML
    public void testi(ActionEvent actionEvent) {
        System.out.println("test");
        Image view = new Image("file:D:\\Etude_Facult\\Java\\Projet Java\\parking-system\\src\\main\\resources\\imgs\\loading-22.gif");
        //ImageView img = new ImageView();
        imgLoginProg.setImage(view);
        /*Label lb = new Label();
        lb.setText(String.valueOf(img));*/
        //primaryButton.setGraphic(img);
        //primaryButton.setContentDisplay(ContentDisplay.TOP);
        //primaryButton.setGraphic(lb);

        progId.setVisible(true);

    }
}
