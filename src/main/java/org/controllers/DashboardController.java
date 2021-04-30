package org.controllers;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import org.models.Car;
import org.models.Parking;

public class DashboardController implements Initializable {

//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;

    @FXML
    private PieChart pieChart;

    @FXML
    private BarChart<?, ?> visitchart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Parking park = new Parking("",10);
        Parking.addCar(new Car());
        Parking.addCar(new Car());
        Parking.addCar(new Car());
        Parking.addCar(new Car());
        iniPieChart(Parking.getState()[1], Parking.getState()[2]);
    }

    private void iniBarChart(){

        XYChart.Series set1 = new XYChart.Series();

        set1.getData().add(new XYChart.Data("Monday",44));
        set1.getData().add(new XYChart.Data("Tuesday",36));
        set1.getData().add(new XYChart.Data("Wednesday",20));
        set1.getData().add(new XYChart.Data("Thursday",15));
        set1.getData().add(new XYChart.Data("Friday",13));
        set1.getData().add(new XYChart.Data("Saturday",50));
        set1.getData().add(new XYChart.Data("Sunday",53));
        visitchart.getData().addAll(set1);
    }
    public void iniPieChart(int x,int y){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Saturé",x),
                new PieChart.Data("Libre",y)
        );
        pieChart.setData(pieChartData);
    }

}