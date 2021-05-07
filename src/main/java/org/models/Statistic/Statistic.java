package org.models.Statistic;

import org.bson.types.ObjectId;

public class Statistic {


    ObjectId id;
    private VisitsNumberPerYear visitsNumberPerYear;



    public Statistic() {
    }

    public Statistic(ObjectId id, VisitsNumberPerYear visitsNumberPerYear) {
        this.id = id;
        this.visitsNumberPerYear = visitsNumberPerYear;
    }

    public ObjectId getId() {
        return id;
    }

    public Statistic setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public VisitsNumberPerYear getVisitsNumberPerYear() {
        return visitsNumberPerYear;
    }

    public Statistic setVisitsNumberPerYear(VisitsNumberPerYear visitsNumberPerYear) {
        this.visitsNumberPerYear = visitsNumberPerYear;
        return this;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", visitsNumberPerYear=" + visitsNumberPerYear +
                '}';
    }
}
