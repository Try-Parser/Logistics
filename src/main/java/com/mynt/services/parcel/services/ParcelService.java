package com.mynt.services.parcel.services;

import com.mynt.services.parcel.constants.Conversion;
import com.mynt.services.parcel.constants.Memory;
import com.mynt.services.parcel.constants.Parcel;
import com.mynt.services.parcel.constants.ParcelType;
import com.mynt.services.parcel.responses.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * Parcel Services
 */
@Service
public class ParcelService {
    @Autowired
    private Memory mem;

    /***
     * Get Quotation which is greater
     * @param kg weight
     * @param h height
     * @param w width
     * @param l length
     * @return ResponseService
     */
    public ResponseService getQuotationOnWhichIsHigher(double kg, double h, double w, double l) {
        ResponseService weight = getQuotationViaKg(kg);
        ResponseService volume = getQuotationViaVolume(h, w, l);

        if(weight.getParcel().getRuleName() == ParcelType.REJECT) {
            return weight;
        }

        return (weight.getTotalCost() >= volume.getTotalCost()) ? weight : volume;
    }

    /***
     * Total Quotation via
     * @param h height
     * @param w width
     * @param l length
     * @return ResponseService
     */
    public ResponseService getQuotationViaVolume(double h, double w, double l) {
        return getQuotationViaKg(getVolume(h, w, l) * Conversion.CM32KG.getCon());
    }

    /***
     * Get Quotation base on Which isGreater Volume Weight or Actual Weight
     * Priority is ActualWeight but when it comes to Size Base Parcel depends on which is greater
     * either Volume Weight or Actual Weight will be applied.
     * @param kilo weight
     * @param h height
     * @param w width
     * @param l length
     * @return ResponseService
     */
    public ResponseService getQuotation(double kilo, double h, double w, double l) {
        ResponseService resp = new ResponseService();

        Parcel reject = getParcelViaType(ParcelType.REJECT);
        Parcel heavy = getParcelViaType(ParcelType.HEAVY_PARCEL);

        if(kilo > reject.getLimiter()) {
            resp.setParcel(reject);
            resp.setTotalCost(0.0);
        } else {
            double volume = getVolume(h, w, l);
            double volumeWeight = volume * Conversion.CM32KG.getCon();

            if(kilo > heavy.getLimiter()) {
                double cost = (volumeWeight > kilo) ?
                    (volume * Conversion.CM32KG.getCon()) * heavy.getCost():
                    (kilo) * heavy.getCost();

                setParcelDetails(heavy, cost, resp);
            } else {
                Parcel large = getParcelViaType(ParcelType.LARGE_PARCEL);
                Parcel medium = getParcelViaType(ParcelType.MEDIUM_PARCEL);

                double limiterForLarge = (large.getLimiter() == -1) ?
                        medium.getLimiter() :
                        large.getLimiter();

                if(volume >= limiterForLarge) {
                    double cost = (volumeWeight > kilo) ?
                        volume * large.getCost():
                        (kilo / Conversion.CM32KG.getCon()) * large.getCost();
                    setParcelDetails(large, cost, resp);
                } else {
                    Parcel small = getParcelViaType(ParcelType.SMALL_PARCEL);
                    if(volume >= small.getLimiter()) {
                        double cost = (volumeWeight > kilo) ?
                            volume * medium.getCost():
                            (kilo / Conversion.CM32KG.getCon()) * medium.getCost();
                        setParcelDetails(medium, cost, resp);
                    } else {
                        double cost = (volumeWeight > kilo) ?
                            volume * small.getCost() :
                            (kilo / Conversion.CM32KG.getCon()) * small.getCost();

                        setParcelDetails(small, cost, resp);
                    }
                }

            }
        }

        return resp;
    }

    /***
     * Setter of Parcel Details
     * @param parcel Parcel
     * @param totalCost double
     * @param response ResponseService
     */
    private void setParcelDetails(Parcel parcel, double totalCost, ResponseService response) {
        response.setParcel(parcel);
        response.setTotalCost(totalCost);
    }

    /***
     * Total Quotation
     * @param kilo Kilogram
     * @return ResponseService
     */
    public ResponseService getQuotationViaKg(double kilo) {
        ResponseService resp = new ResponseService();

        Parcel reject = getParcelViaType(ParcelType.REJECT);
        if(kilo > reject.getLimiter()) {
            resp.setParcel(reject);
            resp.setTotalCost(0);
        } else {
            Parcel heavy = getParcelViaType(ParcelType.HEAVY_PARCEL);
            if (kilo >= heavy.getLimiter()) {
                resp.setParcel(heavy);
                resp.setTotalCost(kilo * heavy.getCost());
            } else {
                Parcel large = getParcelViaType(ParcelType.LARGE_PARCEL);
                Parcel medium = getParcelViaType(ParcelType.MEDIUM_PARCEL);

                double limiterForLarge = 0;

                if(large.getLimiter() == -1) {
                    limiterForLarge = medium.getLimiter() * Conversion.CM32KG.getCon();
                } else {
                    limiterForLarge = large.getLimiter();
                }

                if(kilo >= limiterForLarge) {
                    resp.setParcel(large);
                    resp.setTotalCost((kilo / Conversion.CM32KG.getCon()) * large.getCost());
                } else {
                    Parcel small = getParcelViaType(ParcelType.SMALL_PARCEL);
                    double smallKilo = small.getLimiter() * Conversion.CM32KG.getCon();

                    if(kilo >= smallKilo) {
                        resp.setParcel(medium);
                        resp.setTotalCost((kilo / Conversion.CM32KG.getCon()) * medium.getCost());
                    } else {
                        resp.setParcel(small);
                        resp.setTotalCost((kilo / Conversion.CM32KG.getCon()) * small.getCost());
                    }
                }
            }
        }

        return resp;
    }

    /***
     * Extract Parcel Via Type
     * @param parcelType Type
     * @return Parcel
     */
    private Parcel getParcelViaType(ParcelType parcelType) {
        return mem.parcels.get(parcelType);
    }

    /***
     * Volume Computation
     * @param height double
     * @param width double
     * @param length double
     * @return double
     */
    public double getVolume(double height, double width, double length) {
        return height * width * length;
    }

    /***
     * Get List Parcels
     * @return Memory
     */
    public Memory getParcels() {
        return mem;
    }
}
