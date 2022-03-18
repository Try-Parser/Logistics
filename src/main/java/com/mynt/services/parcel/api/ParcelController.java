package com.mynt.services.parcel.api;

import com.mynt.services.parcel.constants.Memory;
import com.mynt.services.parcel.constants.Parcel;
import com.mynt.services.parcel.responses.ResponseService;
import com.mynt.services.parcel.services.MemoryService;
import com.mynt.services.parcel.services.ParcelService;
import com.mynt.services.parcel.services.VoucherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/***
 * Gateway
 */
@RestController
@RequestMapping("/api/")
public class ParcelController {

    @Autowired
    private ParcelService parcelService;

    @Autowired
    private MemoryService memoryService;

    @Autowired
    private VoucherServices voucherServices;

    @GetMapping("quote")
    @ResponseBody
    public ResponseService getQuoteViaVolumeOrKilo(
            @RequestParam double kg,
            @RequestParam double h,
            @RequestParam double w,
            @RequestParam double l,
            @RequestParam(required = false) String voucherCode) {
        return voucherServices.applyVoucher(voucherCode, parcelService.getQuotation(kg, h, w, l));
    }

    /***
     * ignore this for Testing purposes only
     * @param h
     * @param w
     * @param l
     * @param voucherCode
     * @return
     */
 	@GetMapping("quote/volume")
    @ResponseBody
    public ResponseService getQuoteViaVolume(
            @RequestParam double h,
            @RequestParam double w,
            @RequestParam double l,
            @RequestParam(required = false) String voucherCode) {
        return voucherServices.applyVoucher(voucherCode, parcelService.getQuotationViaVolume(h, w,l));
    }

    /***
     * ignore this for Testing purposes only
     * @param kg
     * @param voucherCode
     * @return
     */
    @GetMapping("quote/kilo")
    @ResponseBody
    public ResponseService getQuoteViaKilo(
            @RequestParam double kg,
            @RequestParam(required = false) String voucherCode) {
         return voucherServices.applyVoucher(voucherCode, parcelService.getQuotationViaKg(kg));
    }

    @GetMapping("parcel/list")
    @ResponseBody
    public Memory getParcelList() {
        return parcelService.getParcels();
    }

    @PatchMapping("parcel/update")
    @ResponseBody
    public Parcel updateParcelDetails(@RequestBody Parcel parcel) {
        return memoryService.setParcel(
            parcel.getRuleName(),
            parcel.getPriority(),
            parcel.getLimiter(),
            parcel.getCondition(),
            parcel.getCost());
    }
}