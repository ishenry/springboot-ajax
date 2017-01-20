package com.nova.sd.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ada on 2017/1/12.
 */
public class DateTimeUtils {


    public static String getCurrentDateToString(String pattern) {

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat fo = new SimpleDateFormat();
        fo.applyPattern(pattern);
        String retVal = "00000000000000";
        try {
            retVal = fo.format(date);
        } catch (Exception e) {
        }
        return retVal;
    }

    public static void main(String args[]) {
        System.out.print(DateTimeUtils.getCurrentDateToString("yyyyMMddHHmmss"));
    }

}

