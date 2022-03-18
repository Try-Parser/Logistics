package com.mynt.services.parcel.utils;

/***
 * String Utility
 */
public final class StringUtils {

    public static boolean isNumber(String value) {
        if(value != null) {
            return value.matches("[0-9]+");
        }
        return false;
    }

}
