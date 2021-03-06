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
import org.models.Statistic.VisitsNumberPerYear;

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
    int[] year = getYearState();
    @FXML
    private PieChart pieChart;

    int[][] lastTowMounths = getLastTowMonthsState();
    int[] lastMounth = lastTowMounths[0];
    int[] beforeLastMounth = lastTowMounths[1];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Random r = new Random();
        iniAreaChart(getVisitsNumberInLastTwoMonths(0), getVisitsNumberInLastTwoMonths(1));
        iniPieChart(Parking.getState()[1], Parking.getState()[2]);
        iniLineChart(getVisitsNumberPerYear());

    }

    /**
     * get the number of visits in last month and return the chart series
     * @param month
     * @return
     */
    public XYChart.Series getVisitsNumberInLastTwoMonths(int month) {
        XYChart.Series seriesMarch = new XYChart.Series();
        seriesMarch.setName(new DateFormatSymbols().getMonths()[getLastTwoMonths()[0] - 1] + "");
        for (int i = 1; i < 29; i++) {
            seriesMarch.getData().add(new XYChart.Data(i, lastTowMounths[month][i]));
        }
        return seriesMarch;
    }

    private void iniAreaChart(XYChart.Series lastSerie, XYChart.Series beforeLastSerie) {
        areaChart.getData().addAll(lastSerie, beforeLastSerie);
    }

    /**
     * get the number of visits by year and return the chart series
     * @return
     */
    private XYChart.Series getVisitsNumberPerYear() {
        XYChart.Series series = new XYChart.Series();
        series.setName(String.valueOf((new Date()).getYear()));
        Random r = new Random();
        for (int i = 1; i < 13; i++) {
            series.getData().add(new XYChart.Data(i, year[i - 1]));
        }
        return series;
    }

    public void iniLineChart(XYChart.Series series) {
        lineChart.getData().add(series);
    }

    /**
     * initialize the PIE CHAR by data passed on parameter
     * @param free
     * @param full
     */
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

    /**
     * get the total of cars entered in a specific day in month
     * @param month
     * @param day
     * @return
     */
    public static int visitsNumberInLastTwoMonths(int month, int day) {
        MongoCollection<Document> historyMongoCollection = DbConnection.database.getCollection("historys");
        return (int) historyMongoCollection.count(regex("dateEntered", day + "/" + month + "/"));
    }

    /**
     * GENERATE THE STATISTIC OF THE YEAR
     * @return
     */
    public static Statistic calculateStatistic() {
        Statistic statistic = new Statistic();
        VisitsNumberPerYear visitsNumberPerYear = new VisitsNumberPerYear();
        List<VisitsNumberPerMonth> listMounths = new ArrayList<VisitsNumberPerMonth>();
        for (int monthId = 0; monthId < 12; monthId++) {
            VisitsNumberPerMonth visitsNumberPerMonth = new VisitsNumberPerMonth();
            List<Integer> list = new ArrayList<Integer>();
             for (int dayId = 0; dayId < 30; dayId++) {
                list.add(visitsNumberInLastTwoMonths(monthId,dayId));
            }
             visitsNumberPerMonth.setDay(list);
            listMounths.add(visitsNumberPerMonth);
        }
        visitsNumberPerYear.setMonths(listMounths);
        visitsNumberPerYear.setYear(LocalDate.now().getYear());
        statistic.setVisitsNumberPerYear(visitsNumberPerYear);
        return statistic;
    }

    /**
     * get the index of last 2 months
     * @return
     */
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

    /**
     * get the total of cars entered in a year by months
     * @return
     */
    public static int[] getYearState() {
        MongoCollection<Statistic> statisticMongoCollection = DbConnection.database.getCollection("statistics", Statistic.class);

        Statistic statisticList = statisticMongoCollection.find().first();
        List<VisitsNumberPerMonth> monthsList = new ArrayList<>();

        int[] data = new int[12];
        for (int monthId = 0; monthId < 12; monthId++) {
            int some = 0;
            for (int dayId = 0; dayId < 29; dayId++) {
                some += statisticList.getVisitsNumberPerYear().getMonths().get(monthId).getDay().get(dayId);
                data[monthId] = some;
            }
        }

        return data;
    }

    /**
     *  get the full year state
     * @return
     */
    public static int[][] getLastTowMonthsState() {
        MongoCollection<Statistic> statisticMongoCollection = DbConnection.database.getCollection("statistics", Statistic.class);
        Statistic statisticList = statisticMongoCollection.find().first();


        int[][] dayData = new int[12][30];

        for (int monthId = 0; monthId < 12; monthId++) {
            for (int dayId = 0; dayId < 29; dayId++) {
                dayData[monthId][dayId] = statisticList.getVisitsNumberPerYear().getMonths().get(monthId).getDay().get(dayId);
            }
        }
        return dayData;
    }




}