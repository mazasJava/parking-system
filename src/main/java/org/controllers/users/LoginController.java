package org.controllers.users;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.mainapp.App;
import org.tasks.LoginTask;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    @FXML
    public TextField emailTextField;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    ProgressIndicator logProg;

    @FXML
    public void switchToCar() throws IOException {
        App.setRoot("car");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logProg.setVisible(false);

    }
    public void alert(String title,String message,String header){
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setContentText(message);
            alert.setHeaderText(header);
            alert.showAndWait();
        });
    }

    public void createCount() throws IOException {
        App.setRoot("register");
    }



    @FXML
    public void login(){
        LoginTask task = new LoginTask(emailTextField.getText(),passwordTextField.getText(),logProg);
        logProg.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
    }

    // Add focus Enter .
    public void clickEnterUserName(ActionEvent actionEvent) {
        passwordTextField.requestFocus();
    }

}