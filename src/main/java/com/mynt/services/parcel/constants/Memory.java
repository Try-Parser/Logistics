package com.mynt.services.parcel.constants;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/***
 * Holds Condition Data;
 */
@Service
@Scope("singleton")
public class Memory {

    @Value("${voucher.api.host}")
    private String voucherHost;

    @Value("${voucher.api.key}")
    private String voucherKey;

    public Map<ParcelType, Parcel> parcels = Map.of(
        ParcelType.REJECT, new Parcel(
            "(Highest)",
            ParcelType.REJECT,
            50,
            "Weight exceeds 50kg",
            0),
        ParcelType.HEAVY_PARCEL, new Parcel(
            "",
            ParcelType.HEAVY_PARCEL,
            10,
            "Weight exceeds 10kg",
            20),
        ParcelType.SMALL_PARCEL, new Parcel(
            "",
            ParcelType.SMALL_PARCEL,
            1500,
            "Volume is less than 1500 cm3",
            0.03),
        ParcelType.MEDIUM_PARCEL, new Parcel(
            "",
            ParcelType.MEDIUM_PARCEL,
            2500,
            "Volume is less than 2500 cm3",
            0.04),
        ParcelType.LARGE_PARCEL, new Parcel("(Lowest)", ParcelType.LARGE_PARCEL,"", 0.05)
    );

    @JsonIgnore
    public String getVoucherHost() {
        return voucherHost;
    }

    @JsonIgnore
    public String getVoucherKey() {
        return voucherKey;
    }

    public Map<ParcelType, Parcel> getParcels() {
        return parcels;
    }

    public void setParcels(Map<ParcelType, Parcel> parcels) {
        this.parcels = parcels;
    }

    public Parcel setParcel(ParcelType parcelType, String priority, double limiter, String condition, double cost) {
        Parcel parcel = this.parcels.get(parcelType);
        parcel.setCondition(condition);
        parcel.setLimiter(limiter);
        parcel.setCost(cost);
        parcel.setPriority(priority);
        return parcel;
    }
}
