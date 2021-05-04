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
    private CategoryAxis x= new CategoryAxis();

    @FXML
    private NumberAxis y = new NumberAxis();

    @FXML
    LineChart<String,Number> lineChart = new LineChart<String,Number>(x,y);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        iniLineChart();
        //        iniPieChart(Parking.getState()[1], Parking.getState()[2]);
    }

    private void iniLineChart(){

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Park A");

        series1.getData().add(new XYChart.Data("Jan", 23));
        series1.getData().add(new XYChart.Data("Feb", 14));
        series1.getData().add(new XYChart.Data("Mar", 15));
        series1.getData().add(new XYChart.Data("Apr", 24));
        series1.getData().add(new XYChart.Data("May", 34));
        series1.getData().add(new XYChart.Data("Jun", 36));
        series1.getData().add(new XYChart.Data("Jul", 22));
        series1.getData().add(new XYChart.Data("Aug", 45));
        series1.getData().add(new XYChart.Data("Sep", 43));
        series1.getData().add(new XYChart.Data("Oct", 17));
        series1.getData().add(new XYChart.Data("Nov", 29));
        series1.getData().add(new XYChart.Data("Dec", 25));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("PARK B");
        series2.getData().add(new XYChart.Data("Jan", 33));
        series2.getData().add(new XYChart.Data("Feb", 34));
        series2.getData().add(new XYChart.Data("Mar", 25));
        series2.getData().add(new XYChart.Data("Apr", 44));
        series2.getData().add(new XYChart.Data("May", 39));
        series2.getData().add(new XYChart.Data("Jun", 16));
        series2.getData().add(new XYChart.Data("Jul", 55));
        series2.getData().add(new XYChart.Data("Aug", 54));
        series2.getData().add(new XYChart.Data("Sep", 48));
        series2.getData().add(new XYChart.Data("Oct", 27));
        series2.getData().add(new XYChart.Data("Nov", 37));
        series2.getData().add(new XYChart.Data("Dec", 29));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("PARK C");
        series3.getData().add(new XYChart.Data("Jan", 44));
        series3.getData().add(new XYChart.Data("Feb", 35));
        series3.getData().add(new XYChart.Data("Mar", 36));
        series3.getData().add(new XYChart.Data("Apr", 33));
        series3.getData().add(new XYChart.Data("May", 31));
        series3.getData().add(new XYChart.Data("Jun", 26));
        series3.getData().add(new XYChart.Data("Jul", 22));
        series3.getData().add(new XYChart.Data("Aug", 25));
        series3.getData().add(new XYChart.Data("Sep", 43));
        series3.getData().add(new XYChart.Data("Oct", 44));
        series3.getData().add(new XYChart.Data("Nov", 45));
        series3.getData().add(new XYChart.Data("Dec", 44));


        XYChart.Series series4 = new XYChart.Series();
        series4.setName("PARK D");
        series4.getData().add(new XYChart.Data("Jan", 40));
        series4.getData().add(new XYChart.Data("Feb", 38));
        series4.getData().add(new XYChart.Data("Mar", 32));
        series4.getData().add(new XYChart.Data("Apr", 30));
        series4.getData().add(new XYChart.Data("May", 39));
        series4.getData().add(new XYChart.Data("Jun", 27));
        series4.getData().add(new XYChart.Data("Jul", 21));
        series4.getData().add(new XYChart.Data("Aug", 27));
        series4.getData().add(new XYChart.Data("Sep", 33));
        series4.getData().add(new XYChart.Data("Oct", 24));
        series4.getData().add(new XYChart.Data("Nov", 49));
        series4.getData().add(new XYChart.Data("Dec", 54));

        lineChart.getData().addAll(series1, series2, series3,series4);



    }


}