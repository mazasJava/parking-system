package org.models.Statistic;

import java.util.Arrays;
import java.util.List;

public class VisitsNumberPerYear {

    private int year;
    private List<Integer> months;


    public VisitsNumberPerYear() {
    }

    public VisitsNumberPerYear(int year, List<Integer> months) {
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

    public List<Integer> getMonths() {
        return months;
    }

    public VisitsNumberPerYear setMonths(List<Integer> months) {
        this.months = months;
        return this;
    }

    @Override
    public String toString() {
        return "VisitsNumberPerYear{" +
                "year=" + year +
//                ", months=" + Arrays.toString(months) +
                '}';
    }
}
