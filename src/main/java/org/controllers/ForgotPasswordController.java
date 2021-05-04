package org.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ForgotPasswordController {

    @FXML
    TextField emailTextField;

    public void ConfirmForgotPassword(ActionEvent actionEvent) {
        ForgotPassword forgotPassword = new ForgotPassword();
        try {
            forgotPassword.sendEmail(emailTextField.getText());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickEnterEmail(ActionEvent actionEvent) {
        emailTextField.requestFocus();
    }
}
