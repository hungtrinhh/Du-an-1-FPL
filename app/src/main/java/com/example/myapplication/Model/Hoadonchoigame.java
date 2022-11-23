package com.example.myapplication.Model;

public class Hoadonchoigame extends Hoadon {
    private String date;
    private String Userid;
    private boolean isSuccess = false;


    public Hoadonchoigame(float cost, String date, String userid) {
        super(cost);
        this.date = date;
        Userid = userid;
    }

    public Hoadonchoigame() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
