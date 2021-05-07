package org.models.Statistic;

import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.List;

public class VisitsNumberPerYear {

    ObjectId id;
    private int year;
    private List<VisitsNumberPerMonth> months;

    public VisitsNumberPerYear() {
    }

    public VisitsNumberPerYear(ObjectId id, int year, List<VisitsNumberPerMonth> months) {
        this.id = id;
        this.year = year;
        this.months = months;
    }

    public ObjectId getId() {
        return id;
    }

    public VisitsNumberPerYear setId(ObjectId id) {
        this.id = id;
        return this;
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

    @Override
    public String toString() {
        return "VisitsNumberPerYear{" +
                "id=" + id +
                ", year=" + year +
                ", months=" + months +
                '}';
    }
}
