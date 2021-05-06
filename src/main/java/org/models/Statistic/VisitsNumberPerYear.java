package org.models.Statistic;

import java.util.Arrays;

public class VisitsNumberPerYear {

    private int year;
    private String []months;


    public VisitsNumberPerYear() {
    }

    public VisitsNumberPerYear(int year, String[] months) {
        this.year = year;
        this.months = months;
    }

    public int getYear() {
        return year;
    }

    public VisitsNumberPerYear setYear(int year) {
        this.year = year;
        return this;
    }

    public String[] getMonths() {
        return months;
    }

    public VisitsNumberPerYear setMonths(String[] months) {
        this.months = months;
        return this;
    }

    @Override
    public String toString() {
        return "VisitsNumberPerYear{" +
                "year=" + year +
                ", months=" + Arrays.toString(months) +
                '}';
    }
}
