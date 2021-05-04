package org.mainapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.clientServer.Client;
import org.clientServer.Server;
import org.controllers.DbConnection;
import org.models.Parking;

import java.io.IOException;

/**
 * JavaFX Main app entry
 */
public class App extends Application {
    private static Scene scene;

    public static String emailRemem = "", passRemem = "";

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("dashboard"), 1150, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        System.out.println("Set rooted: " + fxml);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws IOException {
        DbConnection.connect();
        Parking park = new Parking("this is the parking name",10);
        String hostname = "localhost";
        int port = Integer.parseInt(String.valueOf(8081));
        Client c = new Client(hostname, port);
        c.start();
        launch(args);
//        System.out.println("args launched");
//        Parking park = new Parking("this is the parking name",10);
//        DbConnection.connect();
    }

}