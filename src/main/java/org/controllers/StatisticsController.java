package org.controllers;

import com.mongodb.client.MongoCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;

import static com.mongodb.client.model.Filters.regex;

import org.bson.Document;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {

    @FXML
    private NumberAxis x = new NumberAxis(1, 31, 1);

    @FXML
    private NumberAxis y = new NumberAxis();

    @FXML
    private AreaChart<Number, Number> areaChart = new AreaChart<Number, Number>(x, y);

    @FXML
    private CategoryAxis xB = new CategoryAxis();

    @FXML
    private NumberAxis yB = new NumberAxis();

    @FXML
    private BarChart<String, Number> barChart = new BarChart<String, Number>(xB, yB);

    @FXML
    private NumberAxis xAxis = new NumberAxis();

    @FXML
    private NumberAxis yAxis = new NumberAxis();

    @FXML
    private LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);


    @FXML
    private PieChart pieChart;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        iniAreaChart();
//        iniPieChart();
        iniLineChart();
//        iniBarChart();
    }

//    private void iniAreaChart(){
//
//        XYChart.Series seriesMarch = new XYChart.Series();
//        seriesMarch.setName("March");
//        seriesMarch.getData().add(new XYChart.Data(1, 20));
//        seriesMarch.getData().add(new XYChart.Data(3, 15));
//        seriesMarch.getData().add(new XYChart.Data(6, 13));
//        seriesMarch.getData().add(new XYChart.Data(9, 12));
//        seriesMarch.getData().add(new XYChart.Data(12, 14));
//        seriesMarch.getData().add(new XYChart.Data(15, 18));
//        seriesMarch.getData().add(new XYChart.Data(18, 25));
//        seriesMarch.getData().add(new XYChart.Data(21, 25));
//        seriesMarch.getData().add(new XYChart.Data(24, 23));
//        seriesMarch.getData().add(new XYChart.Data(27, 26));
//        seriesMarch.getData().add(new XYChart.Data(31, 26));
//
//
//        XYChart.Series seriesApril= new XYChart.Series();
//        seriesApril.setName("April");
//        seriesApril.getData().add(new XYChart.Data(1, 4));
//        seriesApril.getData().add(new XYChart.Data(3, 10));
//        seriesApril.getData().add(new XYChart.Data(6, 15));
//        seriesApril.getData().add(new XYChart.Data(9, 8));
//        seriesApril.getData().add(new XYChart.Data(12, 5));
//        seriesApril.getData().add(new XYChart.Data(15, 18));
//        seriesApril.getData().add(new XYChart.Data(18, 15));
//        seriesApril.getData().add(new XYChart.Data(21, 13));
//        seriesApril.getData().add(new XYChart.Data(24, 19));
//        seriesApril.getData().add(new XYChart.Data(27, 21));
//        seriesApril.getData().add(new XYChart.Data(30, 21));
//
//
//
//
//        areaChart.getData().addAll( seriesMarch,seriesApril);
//
//    }
//    public void iniBarChart(){
//        XYChart.Series series1 = new XYChart.Series();
//        series1.setName("Morning");
//        series1.getData().add(new XYChart.Data("Park A", 7));
//        series1.getData().add(new XYChart.Data("Park B", 8));
//        series1.getData().add(new XYChart.Data("Park C", 9));
//        series1.getData().add(new XYChart.Data("Park D", 11));
//
//
//        XYChart.Series series2 = new XYChart.Series();
//        series2.setName("Noon");
//        series2.getData().add(new XYChart.Data("Park A", 2));
//        series2.getData().add(new XYChart.Data("Park B", 3));
//        series2.getData().add(new XYChart.Data("Park C", 1));
//        series2.getData().add(new XYChart.Data("Park D", 6));
//
//        XYChart.Series series3 = new XYChart.Series();
//        series3.setName("Evening");
//        series3.getData().add(new XYChart.Data("Park A", 9));
//        series3.getData().add(new XYChart.Data("Park B", 8));
//        series3.getData().add(new XYChart.Data("Park C", 9));
//        series3.getData().add(new XYChart.Data("Park D", 10));
//
//
//        barChart.getData().addAll(series1, series2, series3);
//
//    }


    public void iniLineChart() {
        XYChart.Series series = new XYChart.Series();
        series.setName("year 2020");
        for (int i = 1; i < 13; i++) {
            series.getData().add(new XYChart.Data(i, visitsNumberPerYear(i)));
        }
        lineChart.getData().add(series);
    }


//    public void iniPieChart(){
//        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
//                new PieChart.Data("free",14),
//                new PieChart.Data("full",36)
//        );
//        pieChart.setData(pieChartData);
//    }

    public static int visitsNumberPerYear(int month) {
        MongoCollection<Document> historyMongoCollection = DbConnection.database.getCollection("historys");
        return (int) historyMongoCollection.count(regex("dateEntered", "/" + month + "/"));
    }





    public static int[] getLastTwoMonths() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int lastMonth = 0;
        int lastTwoMonth = 0;
        int month = localDate.getMonthValue();

        if (month == 1) {
            lastMonth = 12;
            lastTwoMonth = 11;
        } else if (month == 2) {
            lastMonth = month - 1;
            lastTwoMonth = 12;
        }

        lastMonth = month - 1;
        lastTwoMonth = month - 2;

        return new int[]{lastMonth, lastTwoMonth};
    }

    public static void main(String[] args) {
        DbConnection.connect();
//        visitsNumberPerYear(1);
        System.out.println(visitsNumberInLastTwoMonths(getLastTwoMonths()[0], 6));
//        System.out.println(getLastTowMonths()[0]);
    }
}