package org.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {

    @FXML
    private NumberAxis x = new NumberAxis(1, 31, 1);

    @FXML
    private NumberAxis y = new NumberAxis();

    @FXML
    private AreaChart<Number,Number> areaChart = new AreaChart<Number,Number>(x,y);

    @FXML
    private CategoryAxis xB = new CategoryAxis();

    @FXML
    private NumberAxis yB = new NumberAxis();

    @FXML
    private BarChart<String,Number> barChart =new BarChart<String,Number>(xB,yB);

    @FXML
    private NumberAxis xAxis = new NumberAxis();

    @FXML
    private NumberAxis yAxis = new NumberAxis();

    @FXML
    private LineChart<Number,Number> lineChart =new LineChart<Number,Number>(xAxis,yAxis);


    @FXML
    private PieChart pieChart;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       iniAreaChart();
        iniPieChart();
        iniLineChart();
        iniBarChart();
    }

    private void iniAreaChart(){

        XYChart.Series seriesMarch = new XYChart.Series();
        seriesMarch.setName("March");
        seriesMarch.getData().add(new XYChart.Data(1, 20));
        seriesMarch.getData().add(new XYChart.Data(3, 15));
        seriesMarch.getData().add(new XYChart.Data(6, 13));
        seriesMarch.getData().add(new XYChart.Data(9, 12));
        seriesMarch.getData().add(new XYChart.Data(12, 14));
        seriesMarch.getData().add(new XYChart.Data(15, 18));
        seriesMarch.getData().add(new XYChart.Data(18, 25));
        seriesMarch.getData().add(new XYChart.Data(21, 25));
        seriesMarch.getData().add(new XYChart.Data(24, 23));
        seriesMarch.getData().add(new XYChart.Data(27, 26));
        seriesMarch.getData().add(new XYChart.Data(31, 26));


        XYChart.Series seriesApril= new XYChart.Series();
        seriesApril.setName("April");
        seriesApril.getData().add(new XYChart.Data(1, 4));
        seriesApril.getData().add(new XYChart.Data(3, 10));
        seriesApril.getData().add(new XYChart.Data(6, 15));
        seriesApril.getData().add(new XYChart.Data(9, 8));
        seriesApril.getData().add(new XYChart.Data(12, 5));
        seriesApril.getData().add(new XYChart.Data(15, 18));
        seriesApril.getData().add(new XYChart.Data(18, 15));
        seriesApril.getData().add(new XYChart.Data(21, 13));
        seriesApril.getData().add(new XYChart.Data(24, 19));
        seriesApril.getData().add(new XYChart.Data(27, 21));
        seriesApril.getData().add(new XYChart.Data(30, 21));




        areaChart.getData().addAll( seriesMarch,seriesApril);

    }
    public void iniBarChart(){
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Morning");
        series1.getData().add(new XYChart.Data("Park A", 7));
        series1.getData().add(new XYChart.Data("Park B", 8));
        series1.getData().add(new XYChart.Data("Park C", 9));
        series1.getData().add(new XYChart.Data("Park D", 11));


        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Noon");
        series2.getData().add(new XYChart.Data("Park A", 2));
        series2.getData().add(new XYChart.Data("Park B", 3));
        series2.getData().add(new XYChart.Data("Park C", 1));
        series2.getData().add(new XYChart.Data("Park D", 6));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Evening");
        series3.getData().add(new XYChart.Data("Park A", 9));
        series3.getData().add(new XYChart.Data("Park B", 8));
        series3.getData().add(new XYChart.Data("Park C", 9));
        series3.getData().add(new XYChart.Data("Park D", 10));


        barChart.getData().addAll(series1, series2, series3);

    }


    public void iniLineChart(){
        XYChart.Series series = new XYChart.Series();
        series.setName("year 2020");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));

//        lineChart.getData().addAll(series);
       lineChart.getData().add(series);

    }
    public void iniPieChart(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("free",14),
                new PieChart.Data("full",36)
        );
        pieChart.setData(pieChartData);
    }
}
