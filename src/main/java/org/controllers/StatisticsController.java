package org.controllers;

import com.mongodb.client.MongoCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;

import static com.mongodb.client.model.Filters.regex;

import javafx.scene.control.ComboBox;
import org.bson.Document;
import org.models.Parking;
import org.models.Statistic.Statistic;
import org.models.Statistic.VisitsNumberPerMonth;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class StatisticsController implements Initializable {
    @FXML
    public ComboBox parkingList;
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
    private NumberAxis xAxis = new NumberAxis();

    @FXML
    private NumberAxis yAxis = new NumberAxis();

    @FXML
    private LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
    private int[] totalPerMonth = getYearState();
    private int[][] totalMonth = getLastTowMonthsState();

    @FXML
    private PieChart pieChart;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parkingList.setValue(parkingList.getItems().get(0));
        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        Random r = new Random();
                        lineChart.getData().clear();
                        pieChart.getData().clear();
                        areaChart.getData().clear();
                        switch (parkingList.getValue().toString()) {
                            case "park A":
                                iniAreaChart(getVisitsNumberInLastTwoMonths(3), getVisitsNumberInLastTwoMonths(4));
                                iniPieChart(r.nextInt(100), r.nextInt(100));
                                iniLineChart(getVisitsNumberPerYear());
                                break;
                            case "park B":
                                iniAreaChart(getVisitsNumberInLastTwoMonths(6), getVisitsNumberInLastTwoMonths(5));
                                iniPieChart(r.nextInt(100), r.nextInt(100));
                                iniLineChart(getVisitsNumberPerYear());
//                                System.out.println("park B");
                                break;
                            case "park C":
                                iniAreaChart(getVisitsNumberInLastTwoMonths(4), getVisitsNumberInLastTwoMonths(5));
                                iniPieChart(r.nextInt(100), r.nextInt(100));
                                iniLineChart(getVisitsNumberPerYear());
//                                System.out.println("park C");
                                break;
                            case "park D":
                                iniAreaChart(getVisitsNumberInLastTwoMonths(2), getVisitsNumberInLastTwoMonths(3));
                                iniPieChart(r.nextInt(100), r.nextInt(100));
                                iniLineChart(getVisitsNumberPerYear());
//                                System.out.println("park D");
                                break;
                        }
                        System.out.println((parkingList.getValue() + " selected"));
                    }
                };
        // Set on action
        parkingList.setOnAction(event);

    }

    public XYChart.Series getVisitsNumberInLastTwoMonths(int month) {
        XYChart.Series seriesMarch = new XYChart.Series();
        seriesMarch.setName(new DateFormatSymbols().getMonths()[getLastTwoMonths()[0] - 1] + "");
        for (int i = 1; i < 30; i++) {
            seriesMarch.getData().add(new XYChart.Data(i , totalMonth[month][i]));
        }
//        }
        return seriesMarch;
    }

    private void iniAreaChart(XYChart.Series lastSerie, XYChart.Series beforeLastSerie) {
        areaChart.getData().addAll(lastSerie, beforeLastSerie);
    }

    private XYChart.Series getVisitsNumberPerYear() {
        XYChart.Series series = new XYChart.Series();
        series.setName(String.valueOf((new Date()).getYear()));
        Random r = new Random();
        for (int i = 1; i < 13; i++) {
            series.getData().add(new XYChart.Data(i, totalPerMonth[i]));
        }
        return series;
    }

    public void iniLineChart(XYChart.Series series) {
        lineChart.getData().add(series);
    }


    public void iniPieChart(double free, double full) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("free", free),
                new PieChart.Data("full", full)
        );
        pieChart.setData(pieChartData);
    }

    public static int visitsNumberPerYear(int month) {
        MongoCollection<Document> historyMongoCollection = DbConnection.database.getCollection("historys");
        return (int) historyMongoCollection.count(regex("dateEntered", "/" + month + "/"));
    }

    public static int visitsNumberInLastTwoMonths(int month, int day) {
        MongoCollection<Document> historyMongoCollection = DbConnection.database.getCollection("historys");
        return (int) historyMongoCollection.count(regex("dateEntered", day + "/" + month + "/"));
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
            lastMonth = 1;
            lastTwoMonth = 12;
        }

        lastMonth = month - 1;
        lastTwoMonth = month - 2;

        return new int[]{lastMonth, lastTwoMonth};
    }


//    public static void getData() {
//
//    }


    public static int[] getYearState(){
        MongoCollection<Statistic> statisticMongoCollection = DbConnection.database.getCollection("statistics", Statistic.class);

        Statistic statisticList = statisticMongoCollection.find().first();
        List<VisitsNumberPerMonth> monthsList = new ArrayList<>();

        int [] data = new int[12];
        for (int monthId = 0; monthId < 12; monthId++) {
            int some = 0;
            for (int dayId = 0; dayId < 29; dayId++){
                some += statisticList.getVisitsNumberPerYear().getMonths().get(monthId).getDay().get(dayId);
                data[monthId] = some;
            }
        }

        return data;
    }


    public static int[][] getLastTowMonthsState(){
        MongoCollection<Statistic> statisticMongoCollection = DbConnection.database.getCollection("statistics", Statistic.class);
        Statistic statisticList = statisticMongoCollection.find().first();
        List<VisitsNumberPerMonth> monthsList = new ArrayList<>();


        int [] MonthData = new int[12];
        int [][] dayData = new int[12][30];

        for (int monthId = 0; monthId < 12; monthId++) {
            for (int dayId = 0; dayId < 29; dayId++){
                dayData[monthId][dayId] = statisticList.getVisitsNumberPerYear().getMonths().get(monthId).getDay().get(dayId);
            }
        }

        return dayData;

    }


    public static void main(String[] args) {

        DbConnection.connect();

        System.out.println(getLastTowMonthsState()[3][3]);


    }


}