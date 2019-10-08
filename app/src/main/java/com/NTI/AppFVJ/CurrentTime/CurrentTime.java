package com.NTI.AppFVJ.CurrentTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CurrentTime {
    public static String GetCurrentTime(String format){
        SimpleDateFormat date_timeFormat = new SimpleDateFormat(format);

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Date time = calendar.getTime();

        String currentTime = date_timeFormat.format(time);

        return currentTime;
    }

    public static String GetOnlyTime(String DateTime){
        SimpleDateFormat date_timeFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentTime = date_timeFormat.format(DateTime);

        return currentTime;
    }
}
