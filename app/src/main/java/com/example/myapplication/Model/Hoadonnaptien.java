package com.example.myapplication.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Hoadonnaptien extends Hoadon {
    String userId;
    String date;

    public Hoadonnaptien(float cost, String userId, String date) {
        super(cost);
        this.userId = userId;
        this.date = date;
    }

    public Hoadonnaptien() {

    }

    public String getUserId() {
        return userId;
    }

    public void setDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        this.date = dateFormat.format(date);
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
