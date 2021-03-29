package org.mainapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controllers.DbConnection;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    // soufiane
    private double xOfsset;
    private double yOfsset;


    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("car"), 1150, 499);
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        DbConnection.connect();
        launch();
    }

}