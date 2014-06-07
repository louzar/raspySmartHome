package com.dor.smarthome.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Author: kolpakov.and@gmail.com
 * Created by dor on 07.06.2014.
 */
public abstract class SensorUtils {

    private static DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public static DateFormat getDateFormat() {
        return dateFormat;
    }
}
