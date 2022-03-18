package com.mynt.services.parcel.responses;

import com.mynt.services.parcel.constants.Parcel;

/***
 * Response Entity
 */
public class ResponseService {
    private Parcel parcel;
    private Voucher voucher;
    private double totalCost;

    public Parcel getParcel() {
        return parcel;
    }

    public void setParcel(Parcel parcel) {
        this.parcel = parcel;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

}
