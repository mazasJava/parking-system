package org.models.Statistic;

import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.List;

public class VisitsNumberPerYear {

    private int year;
    private List<VisitsNumberPerMonth> months;

    public VisitsNumberPerYear() {
    }

    public VisitsNumberPerYear(int year, List<VisitsNumberPerMonth> months) {
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

    public List<VisitsNumberPerMonth> getMonths() {
        return months;
    }

    public VisitsNumberPerYear setMonths(List<VisitsNumberPerMonth> months) {
        this.months = months;
        return this;
    }

}
