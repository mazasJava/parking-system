package org.models.Statistic;

import org.bson.types.ObjectId;

import java.util.List;

public class VisitsNumberPerMonth {

    ObjectId id;
    private List<Integer> day;


    public VisitsNumberPerMonth() {
    }

    public VisitsNumberPerMonth(ObjectId id, List<Integer> day) {
        this.id = id;
        this.day = day;
    }

    public ObjectId getId() {
        return id;
    }

    public VisitsNumberPerMonth setId(ObjectId id) {
        this.id = id;
        return this;
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
                "id=" + id +
                ", day=" + day +
                '}';
    }
}
