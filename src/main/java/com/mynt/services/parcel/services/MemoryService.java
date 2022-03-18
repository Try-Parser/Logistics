package com.mynt.services.parcel.services;

import com.mynt.services.parcel.constants.Memory;
import com.mynt.services.parcel.constants.Parcel;
import com.mynt.services.parcel.constants.ParcelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * Memory Details
 */
@Service
public class MemoryService {
    @Autowired
    private Memory mem;

    /***
     * Update Parcel Details in memory
     * @param parcelType ParcelType
     * @param priority String
     * @param limiter double
     * @param condition String
     * @param cost double
     * @return Parcel
     */
    public Parcel setParcel(ParcelType parcelType, String priority, double limiter, String condition, double cost) {
        return mem.setParcel(parcelType, priority, limiter, condition, cost);
    }
}
