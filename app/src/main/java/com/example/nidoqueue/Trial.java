package com.example.nidoqueue;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.ZonedDateTime;

public class Trial {
    private ZonedDateTime zonedDateTime;
    private double data;
    private String id;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Trial(Double data, String id) {
        this.zonedDateTime = ZonedDateTime.now();
        this.data = data;
        this.id = id;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public double getData() {
        return data;
    }

    public String getId(){
        return id;
    }


    // THESE ARE JUST FOR TESTING
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private ZonedDateTime randomizeDate(){
//        int year = createRandomIntBetween(2000, 2021);
//        int month = createRandomIntBetween(1, 12);
//        int day = createRandomIntBetween(1, 28);
//        int hour = createRandomIntBetween(0, 23);
//        int minute = createRandomIntBetween(0, 59);
//        int second = createRandomIntBetween(0, 59);
//        LocalDateTime ldt = LocalDateTime.of(year, month, day, hour, minute, second);
//
//
//        return ldt.atZone(ZoneId.systemDefault());
//
//    }
//
//    private int createRandomIntBetween(int start, int end){
//        return start + (int) Math.round(Math.random() * (end - start));
//    }
}
