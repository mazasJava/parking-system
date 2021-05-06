package org.models.Statistic;

public class Statistic {


    private VisitsNumberInTwoLastMonths visitsNumberInTwoLastMonths;
    private VisitsNumberPerYear visitsNumberPerYear;


    public Statistic() {
    }

    public Statistic(VisitsNumberInTwoLastMonths visitsNumberInTwoLastMonths, VisitsNumberPerYear visitsNumberPerYear) {
        this.visitsNumberInTwoLastMonths = visitsNumberInTwoLastMonths;
        this.visitsNumberPerYear = visitsNumberPerYear;
    }

    public VisitsNumberInTwoLastMonths getVisitsNumberInTwoLastMonths() {
        return visitsNumberInTwoLastMonths;
    }

    public Statistic setVisitsNumberInTwoLastMonths(VisitsNumberInTwoLastMonths visitsNumberInTwoLastMonths) {
        this.visitsNumberInTwoLastMonths = visitsNumberInTwoLastMonths;
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
                "visitsNumberInTwoLastMonths=" + visitsNumberInTwoLastMonths +
                ", visitsNumberPerYear=" + visitsNumberPerYear +
                '}';
    }
}
