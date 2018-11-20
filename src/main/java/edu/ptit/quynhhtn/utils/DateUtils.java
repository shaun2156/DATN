package edu.ptit.quynhhtn.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");

    public static Date parseDate(String date){
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static String formatDate(Date date){
        try {
            return sdf.format(date);
        }
        catch (Exception ex){
            return "";
        }
    }
}
