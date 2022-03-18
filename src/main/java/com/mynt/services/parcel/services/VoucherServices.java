package com.mynt.services.parcel.services;

import com.mynt.services.parcel.constants.Memory;
import com.mynt.services.parcel.responses.ResponseService;
import com.mynt.services.parcel.responses.Voucher;
import com.mynt.services.parcel.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/***
 * Voucher Services
 */
@Service
public class VoucherServices {

    @Autowired
    private Memory mem;

    private RestTemplate restTemplate = new RestTemplate();

    /***
     * Apply voucher
     * @param voucherCode String
     * @param responseService ResponseService
     * @return ResponseService
     */
    public ResponseService applyVoucher(String voucherCode, ResponseService responseService) {
        if(voucherCode != null) {
            Voucher voucher = getExistVoucher(voucherCode);
            responseService.setVoucher(voucher);
            if (!DateUtils.isExpired(voucher.getExpiry())) {
                responseService.setTotalCost(responseService.getTotalCost() - voucher.getDiscount());
            }
        }

        return responseService;
    }

    /***
     * Request Voucher Details;
     * @param voucherCode String
     * @return Voucher
     */
    public Voucher getExistVoucher(String voucherCode) {
        Voucher response = restTemplate.getForObject(
            mem.getVoucherHost() + "/" + voucherCode + "?key=" + mem.getVoucherKey(),
            Voucher.class
        );

        return response;
    }

}
