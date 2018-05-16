package com.example.a447224.weathersample.utils;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Utils {
    public static final String DEGREE_CELSIUS = "°C";

    public static String convertKelvinToDegree(Double tempKelvin) {
        String temp;
        Double tempInCelsius;
        tempInCelsius = tempKelvin - Constants.KELVIN_CELSIUS_DIFF;
        temp = Math.round(tempInCelsius) + DEGREE_CELSIUS;
        return temp;
    }
    public static String convertTimeInMilliSecsTODateTime(long timeInMills) {
        String convertedTime = "";
        Date date = new Date(timeInMills);
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.UI_DATE_FORMAT);
        convertedTime = dateFormat.format(date);
        return convertedTime;
    }
}
