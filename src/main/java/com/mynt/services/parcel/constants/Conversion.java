package com.mynt.services.parcel.constants;

/***
 * Conversion Rate
 */
public enum Conversion {
    // 1 Cubic centimeter to Kilograms
    CM32KG(0.001);

    private double con;

    Conversion(double v) {
        this.con = v;
    }

    public double getCon() {
        return con;
    }
}
