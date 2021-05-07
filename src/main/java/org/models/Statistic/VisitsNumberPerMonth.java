package org.models.Statistic;

import org.bson.types.ObjectId;

import java.util.List;

public class VisitsNumberPerMonth {

    private List<Integer> day;


    public VisitsNumberPerMonth() {
    }

    public VisitsNumberPerMonth(List<Integer> day) {
        this.day = day;
    }

    public List<Integer> getDay() {
        return day;
    }

    public VisitsNumberPerMonth setDay(List<Integer> day) {
        this.day = day;
        return this;
    }

    @Override
    public String toString() {
        return "VisitsNumberPerMonth{" +
                "day=" + day +
                '}';
    }
}
