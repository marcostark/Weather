package br.com.marcosouza.weather.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Helper {

    public static String convertDate(long currentDate){
        SimpleDateFormat sdf = new SimpleDateFormat("kk:mm:ss");

        Date dateSunset = new Date(currentDate*1000L);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-3"));

        String formattedDateSunset = sdf.format(dateSunset);
        return formattedDateSunset;
    }

}
