package org.models.Statistic;

import java.util.Arrays;

public class VisitsNumberInTwoLastMonths {
    private int month;
    private int[] points;


    public VisitsNumberInTwoLastMonths() {
    }

    public VisitsNumberInTwoLastMonths(int month, int[] points) {
        this.month = month;
        this.points = points;
    }

    public int getMonth() {
        return month;
    }

    public int[] getPoints() {
        return points;
    }

    public VisitsNumberInTwoLastMonths setMonth(int month) {
        this.month = month;
        return this;
    }

    public VisitsNumberInTwoLastMonths setPoints(int[] points) {
        this.points = points;
        return this;
    }

    @Override
    public String toString() {
        return "VisitsNumberInTwoLastMonths{" +
                "month=" + month +
                ", points=" + Arrays.toString(points) +
                '}';
    }
}
