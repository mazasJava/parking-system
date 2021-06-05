package org.bootstrap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.clientServer.Server;
import org.controllers.DbConnection;
import org.controllers.MenuBarController;

import java.io.IOException;

/**
 * JavaFX Main app entry
 */
public class App extends Application {
    private static Scene scene;


    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 1150, 600);
        stage.setScene(scene);
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

    /**
     * MAIN BOOTSTRAP
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        DbConnection.connect();
        Server server = new Server();
        server.start();
        launch(args);
        MenuBarController.btnLogOut.setOnAction(v -> {
            try {
                App.setRoot("login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}