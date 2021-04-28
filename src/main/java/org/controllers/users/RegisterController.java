package org.controllers.users;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.mainapp.App;
import org.tasks.RegisterTask;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    public Button loginButton;
    @FXML
    TextField fullNameTextField;
    @FXML
    TextField emailTextField;
    @FXML
    TextField passwordTextField;
    @FXML
    PasswordField confirmPasswordTextField;
    @FXML
    ProgressIndicator logProRegister;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logProRegister.setVisible(false);
        fullNameTextField.requestFocus();
    }
    @FXML
    public void creatUser() {
        RegisterTask task = new RegisterTask(fullNameTextField.getText(),emailTextField.getText(),passwordTextField.getText(),confirmPasswordTextField.getText(),logProRegister);
        logProRegister.progressProperty().bind(task.progressProperty());
        new Thread(task).start();

    }
    @FXML
    public void ihaveaacount() throws IOException {
        App.setRoot("login");
    }

}