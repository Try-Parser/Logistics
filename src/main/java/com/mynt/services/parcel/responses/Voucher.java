package com.mynt.services.parcel.responses;

import java.util.Date;

/***
 * Voucher Entity
 */
public class Voucher {
    private String code;
    private double discount;
    private Date expiry;

    public Voucher() {
    }

    public Voucher(String code, double discount, Date expiry) {
        this.code = code;
        this.discount = discount;
        this.expiry = expiry;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }
}
