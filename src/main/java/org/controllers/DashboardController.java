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

    public void setParksTotal(String totalA, String totalB, String totalC, String totalD) {
        txtTotalA.setText(totalA);
        txtTotalB.setText(totalB);
        txtTotalC.setText(totalC);
        txtTotalD.setText(totalD);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setParksTotal(""+(new Random()).nextInt(1000), ""+(new Random()).nextInt(1000), ""+(new Random()).nextInt(1000), ""+(new Random()).nextInt(1000));
        iniLineChart(getTotalPerMonth1("park A",generateData()), getTotalPerMonth1("park B",generateData()), getTotalPerMonth1("park C",generateData()), getTotalPerMonth1("park D",generateData()));
    }

    public int[] generateData() {
        Random r = new Random();
        int[] array = new int[12];
        for(int i=0;i<12;i++){
            array[i] = r.nextInt(100);
        }
        return array;
    }


    public XYChart.Series getTotalPerMonth1(String name, int[] values) {
        String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        XYChart.Series series1 = new XYChart.Series();
        series1.setName(name);
        for (int i = 0; i < 12; i++) {
            series1.getData().add(new XYChart.Data(months[i], values[i]));
        }
        return series1;
    }

    private void iniLineChart(XYChart.Series seriesA, XYChart.Series seriesB, XYChart.Series seriesC, XYChart.Series seriesD) {
        lineChart.getData().addAll(seriesA, seriesB, seriesC, seriesD);
    }


}