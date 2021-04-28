package org.controllers.users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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
    CheckBox chkRememberMe;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logProg.setVisible(false);

        if(!App.emailRemem.equals("") || !App.passRemem.equals("")){
            chkRememberMe.setSelected(true);
            emailTextField.setText(App.emailRemem);
            passwordTextField.setText(App.passRemem);
        }
        else {
            chkRememberMe.setSelected(false);
        }
    }

    @FXML
    public void login(){
        LoginTask task = new LoginTask(emailTextField.getText(),passwordTextField.getText(),logProg);
        logProg.progressProperty().bind(task.progressProperty());
        new Thread(task).start();

        if(chkRememberMe.isSelected()){
            App.emailRemem = emailTextField.getText();
            App.passRemem = passwordTextField.getText();
            System.out.println("is checked" + chkRememberMe.isSelected());
        }else {
            App.emailRemem = "";
            App.passRemem = "";
        }

    }

    // Add focus Enter .
    public void clickEnterUserName() {
        passwordTextField.requestFocus();
    }

    public void createCount(MouseEvent mouseEvent) throws IOException {
        App.setRoot("register");
    }
}