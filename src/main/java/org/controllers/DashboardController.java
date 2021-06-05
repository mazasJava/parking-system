package org.controllers;


import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.text.Text;
import org.models.Car;
import org.models.Parking;

public class DashboardController implements Initializable {


    @FXML
    private Text txtTotalA, txtTotalB, txtTotalC, txtTotalD;
    @FXML
    private CategoryAxis x = new CategoryAxis();

    @FXML
    private NumberAxis y = new NumberAxis();

    @FXML
    LineChart<String, Number> lineChart = new LineChart<String, Number>(x, y);
    int[] year =  StatisticsController.getYearState();

    /**
     *  set the total of last 4 months in the specific charts
     * @param monthA
     * @param monthB
     * @param monthC
     * @param monthD
     */
    public void setParksTotal(String monthA, String monthB, String monthC, String monthD) {
        txtTotalA.setText(monthA);
        txtTotalB.setText(monthB);
        txtTotalC.setText(monthC);
        txtTotalD.setText(monthD);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setParksTotal(""+year[0], ""+year[2], ""+year[3], ""+year[4]);
        iniLineChart(getTotalPerMonth1("park",year));
    }


    /**
     * get the total of cars in within the year
     * @param name
     * @param values
     * @return
     */
    public XYChart.Series getTotalPerMonth1(String name, int[] values) {
        String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        XYChart.Series series1 = new XYChart.Series();
        series1.setName(name);
        for (int i = 0; i < 12; i++) {
            series1.getData().add(new XYChart.Data(months[i], values[i]));
        }
        return series1;
    }

    private void iniLineChart(XYChart.Series seriesA) {
        lineChart.getData().add(seriesA);
    }


}