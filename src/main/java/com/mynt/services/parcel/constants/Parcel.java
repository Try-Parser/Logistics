package com.mynt.services.parcel.constants;

import com.fasterxml.jackson.annotation.JsonIgnore;

/***
 * Parcel Holder
 */
public class Parcel {
    private String priority;
    private ParcelType ruleName;
    private String condition;
    private double limiter;
    private double cost;

    public Parcel() {
    }

    public Parcel(String priority, ParcelType ruleName, double limiter, String condition, double cost) {
        this.limiter = limiter;
        this.priority = priority;
        this.ruleName = ruleName;
        this.condition = condition;
        this.cost = cost;
    }

    public Parcel(String priority, ParcelType ruleName, String condition, double cost) {
        this(priority, ruleName, -1, condition, cost);
    }

    public double getLimiter() {
        return limiter;
    }

    public void setLimiter(double limiter) {
        this.limiter = limiter;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public ParcelType getRuleName() {
        return ruleName;
    }

    public void setRuleName(ParcelType ruleName) {
        this.ruleName = ruleName;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
