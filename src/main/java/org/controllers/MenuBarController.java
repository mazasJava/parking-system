package org.controllers;



import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import org.bootstrap.App;

import java.io.IOException;

public class MenuBarController {

    @FXML
    private Button btnswitchToParkingDashboard;

    @FXML
    private Button btnswitchToParkingCar;

    @FXML
    private Button btnswitchToParkingState;

    @FXML
    private Button btnswitchToParkingStatistics;

    @FXML
    private Button btnswitchToErrLog;

    @FXML
    private Button btnswitchToSettings;

    @FXML
    private Button btnClose;

    public void handleClick(MouseEvent event) throws IOException {
        if(event.getSource() == btnswitchToParkingDashboard){
            App.setRoot("dashboard");
//            btnswitchToParkingDashboard.set
        }
        else if(event.getSource() == btnswitchToParkingCar){
            App.setRoot("car");
        }
        else if(event.getSource() == btnswitchToParkingState){
            App.setRoot("parkingState");
        }
        else if(event.getSource() == btnswitchToParkingStatistics){
            App.setRoot("statistics");
        }
        else if(event.getSource() == btnswitchToErrLog){
            App.setRoot("logProgError");
        }
        else if(event.getSource() == btnswitchToSettings){
            App.setRoot("settings");
        }else if(event.getSource() == btnClose){
            App.setRoot("login");
        }
    }
}